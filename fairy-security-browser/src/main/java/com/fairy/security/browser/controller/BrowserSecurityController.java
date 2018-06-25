package com.fairy.security.browser.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.fairy.security.core.authentication.common.SecurityConstants;
import com.fairy.security.core.properties.SecurityProperties;
import com.fairy.security.core.social.support.SocialUserInfo;
import com.fairy.security.core.support.SimpleResponse;

@RestController
public class BrowserSecurityController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private RequestCache httpSessionRequestCache;
	@Autowired
	private RedirectStrategy defaultRedirectStrategy;
	@Autowired
	private SecurityProperties securityProperties;
	/**
	 * 帮助从session中存取social的user信息(实际是对social的connection进行存取)
	 */
	@Autowired
	private ProviderSignInUtils providerSignInUtils;
	
	@RequestMapping(SecurityConstants.DEFAULT_FORM_LOGIN_URL)
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public String requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		SavedRequest savedRequest = httpSessionRequestCache.getRequest(request, response);
		if (savedRequest != null) {
			String target = savedRequest.getRedirectUrl();
			logger.info("引发跳转的url是" + target);
			if (StringUtils.endsWithIgnoreCase(target, ".html")) {
				defaultRedirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginUrl());
			}
		}
		
		return null;
	}
	
	
	@GetMapping("/social/user")
	public SocialUserInfo getSocialUserInfo(HttpServletRequest request){
		SocialUserInfo userInfo = new SocialUserInfo();
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
		userInfo.setProviderId(connection.getKey().getProviderId());
		userInfo.setProviderUesrId(connection.getKey().getProviderUserId());
		userInfo.setNickname(connection.getDisplayName());
		userInfo.setAvatar(connection.getImageUrl());
		return userInfo;
	}
}
