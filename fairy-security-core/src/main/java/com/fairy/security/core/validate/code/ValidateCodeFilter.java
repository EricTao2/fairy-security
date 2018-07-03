package com.fairy.security.core.validate.code;

import java.io.IOException;
import java.util.HashMap;
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
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fairy.security.core.properties.SecurityProperties;

/**
 * 验证码过滤器，负责校验验证码
 * @author Administrator
 *
 */
@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	private SecurityProperties securityProperties;
	@Autowired
	private ValidateCodeProcessorHolder validateCodeProcessorHolder;
	
	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	
	private Map<String, ValidateCodeType> urlMap = new HashMap<>();
	
	/**
	 * 解析Properties配置参数，添加需要拦截并校验验证码的url
	 */
	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		addUrlToMap(securityProperties.getCode().getImage().getUrl(), ValidateCodeType.IMAGE);
		addUrlToMap(securityProperties.getCode().getSms().getUrl(), ValidateCodeType.SMS);
		
	}
	/**
	 * 
	 * @Title: addUrlToMap   
	 * @Description: 根据验证码类型，添加对应的拦截url  
	 * @param: @param urlString
	 * @param: @param type      
	 * @return: void      
	 * @throws
	 */
	protected void addUrlToMap(String urlString, ValidateCodeType type) {
		if (StringUtils.isNotBlank(urlString)) {
			String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
			for (String url : urls) {
				urlMap.put(url, type);
			}
		}
	}
	/**
	 * 查找对应验证码处理器，并调用validate方法校验。验证通过则调用后续过滤器链
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		ValidateCodeType type = getValidateCodeType(request);
		if (type != null) {
			logger.info("校验请求(" + request.getRequestURI() + ")中的验证码，类型为" + type);
			try {
				validateCodeProcessorHolder.findValidateCodeProcessor(type)
					.validate(new ServletWebRequest(request));
				logger.info("校验通过");
			} catch (ValidateCodeException exception) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
				return;
			}
		}
		filterChain.doFilter(request, response);
	}

	/**   
	 * @Title: getValidateCodeType   
	 * @Description: 获取请求的验证码类型   
	 * @param: @param request
	 * @param: @return      
	 * @return: ValidateCodeType      
	 * @throws   
	 */
	private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
		ValidateCodeType result = null;
		if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
			Set<String> urls = urlMap.keySet();
			for (String url : urls) {
				if (antPathMatcher.match(url, request.getRequestURI())) {
					result = urlMap.get(url);
				}
			}
		}
		return result;
	}
	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}

	public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}
	
}
