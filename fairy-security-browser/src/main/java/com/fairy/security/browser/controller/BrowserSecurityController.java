package com.fairy.security.browser.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fairy.security.core.properties.SecurityProperties;

@RestController
public class BrowserSecurityController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@RequestMapping("/authentication/require")
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public String requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if (savedRequest != null) {
			String target = savedRequest.getRedirectUrl();
			logger.info("引发跳转的url是" + target);
			if (StringUtils.endsWithIgnoreCase(target, ".html")) {
				redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
			}
		}
		
		return null;
	}
}
