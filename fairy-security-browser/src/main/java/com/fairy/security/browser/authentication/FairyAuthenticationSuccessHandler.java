package com.fairy.security.browser.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fairy.security.core.properties.SecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;


public class FairyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * 
	 */
	public FairyAuthenticationSuccessHandler() {
		super();
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
