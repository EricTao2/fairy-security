package com.fairy.security.core.properties;

import com.fairy.security.core.authentication.common.SecurityConstants;

public class BrowserProperties {
	
	private SessionProperties session = new SessionProperties();
	
	private String loginUrl = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

	private String logoutUrl = SecurityConstants.DEFAULT_LOGOUT_PAGE_URL;
	
	private String signUpUrl = SecurityConstants.DEFAULT_SIGNUpUrl_PAGE_URL;
		
	private int rememberMeSeconds = 60 * 60 * 24;
	


	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}

	public SessionProperties getSession() {
		return session;
	}

	public void setSession(SessionProperties session) {
		this.session = session;
	}

	public String getLogoutUrl() {
		return logoutUrl;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	public String getSignUpUrl() {
		return signUpUrl;
	}

	public void setSignUpUrl(String signUpUrl) {
		this.signUpUrl = signUpUrl;
	}
	
	
	
}
