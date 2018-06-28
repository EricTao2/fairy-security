package com.fairy.security.core.properties;

import com.fairy.security.core.authentication.common.SecurityCoreConstants;

public class BrowserProperties {
	
	private SessionProperties session = new SessionProperties();
	
	private String loginUrl = SecurityCoreConstants.DEFAULT_LOGIN_PAGE_URL;
	
	private String loginSuccessUrl = SecurityCoreConstants.DEFAULT_LOGOUT_PAGE_URL;

	private String logoutSuccessUrl = SecurityCoreConstants.DEFAULT_LOGOUT_PAGE_URL;
	
	private String signUpUrl = SecurityCoreConstants.DEFAULT_SIGNUpUrl_PAGE_URL;
		
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

	public String getSignUpUrl() {
		return signUpUrl;
	}

	public void setSignUpUrl(String signUpUrl) {
		this.signUpUrl = signUpUrl;
	}

	public String getLoginSuccessUrl() {
		return loginSuccessUrl;
	}

	public void setLoginSuccessUrl(String loginSuccessUrl) {
		this.loginSuccessUrl = loginSuccessUrl;
	}

	public String getLogoutSuccessUrl() {
		return logoutSuccessUrl;
	}

	public void setLogoutSuccessUrl(String logoutSuccessUrl) {
		this.logoutSuccessUrl = logoutSuccessUrl;
	}
	
	
	
}
