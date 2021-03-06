package com.fairy.security.browser.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 登录成功的处理
 * @author Administrator
 *
 */
public class FairyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * 
	 */
	public FairyAuthenticationSuccessHandler(String defaultSuccessUrl) {
		super();
		setDefaultTargetUrl(defaultSuccessUrl);
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		logger.info("登录成功");
		String accept =request.getHeader("accept");
		if (accept.contains("text/html")) {
			super.onAuthenticationSuccess(request, response, authentication);
		} else {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(authentication));
		}
		
	}

}
