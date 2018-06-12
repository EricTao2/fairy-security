package com.fairy.security.core.validate.code;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fairy.security.core.properties.SecurityProperties;
import com.fairy.security.core.validate.code.image.ImageCode;

@Component
public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean {

	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	private Set<String> urls = new HashSet<>();
	@Autowired
	private SecurityProperties securityProperties;
	
	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	@Autowired
	private Map<String, ValidateCodeProcessor> validateCodeProcessors;
	
	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrl(), ",");
		for (String configUrl : configUrls) {
			urls.add(configUrl);
		}
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		boolean isDoFilter = false;
		for (String url : urls) {
			if (antPathMatcher.match(url, request.getRequestURI())) {
				isDoFilter = true;
				break;
			}
		}
		if (isDoFilter) {
			try {
				validate(new ServletWebRequest(request));
			} catch (ValidateCodeException exception) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
				return;
			}
		}
		filterChain.doFilter(request, response);
	}

	private void validate(ServletWebRequest request) throws ServletRequestBindingException {
		
		ValidateCode imageCode = (ImageCode)sessionStrategy.getAttribute(request, ValidateCodeProcessor.SESSION_KEY_PREFIX + "SMS");
		String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "smsCode");
		if (StringUtils.isBlank(codeInRequest)) {
			throw new ValidateCodeException("验证码不能为空");
		}
		if (imageCode == null) {
			throw new ValidateCodeException("验证码失效");
		}
		if (imageCode.isExpired()) {
			throw new ValidateCodeException("验证码已过期，请重新验证");
		}
		if (!StringUtils.equalsIgnoreCase(imageCode.getCode(), codeInRequest)) {
			throw new ValidateCodeException("验证码输入错误");
		}
		sessionStrategy.removeAttribute(request, ValidateCodeProcessor.SESSION_KEY_PREFIX + "SMS");
	}

	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}

	public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}
	
}
