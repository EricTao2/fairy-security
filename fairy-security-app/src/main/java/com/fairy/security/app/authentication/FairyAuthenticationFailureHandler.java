package com.fairy.security.app.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fairy.security.core.properties.LoginType;
import com.fairy.security.core.properties.SecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;


public class FairyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	private LoginType loginType;
	

	/**
	 * 
	 */
	public FairyAuthenticationFailureHandler(String defaultFailureUrl, LoginType loginType) {
		super(defaultFailureUrl);
		this.loginType = loginType;
	}
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		logger.info("登录失败");
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		if (LoginType.JSON.equals(loginType)) {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(exception.getMessage()));

		} else {
			super.onAuthenticationFailure(request, response, exception);
		}

	}

}
