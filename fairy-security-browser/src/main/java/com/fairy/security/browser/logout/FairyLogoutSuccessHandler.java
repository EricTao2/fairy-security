/**
 * 
 */
package com.fairy.security.browser.logout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.fairy.security.core.support.SimpleResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Fairy自定义logout成功后的处理
 * @author Administrator
 *
 */
public class FairyLogoutSuccessHandler implements LogoutSuccessHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private String logoutUrl;
	/**
	 * 重定向策略
	 */
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	private ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * 
	 */
	public FairyLogoutSuccessHandler(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		logger.info("退出成功");
		if (StringUtils.isBlank(logoutUrl)) {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse("退出成功")));
		} else {
			redirectStrategy.sendRedirect(request, response, logoutUrl);
		}
	}

}
