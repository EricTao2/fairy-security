package com.fairy.security.browser.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import com.fairy.security.core.support.SimpleResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 登录校验失败的处理
 * @author Administrator
 *
 */
public class FairyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	

	/**
	 * 
	 */
	public FairyAuthenticationFailureHandler(String defaultFailureUrl) {
		super(defaultFailureUrl+"?error=true");
	}
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		logger.info("登录失败");
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		String accept =request.getHeader("accept");
		if (accept.contains("text/html")) {
			super.onAuthenticationFailure(request, response, exception);
		} else {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));

		}

	}

}
