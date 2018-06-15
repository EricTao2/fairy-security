package com.fairy.security.core.properties;

import com.fairy.security.core.authentication.common.SecurityConstants;

public class BrowserProperties {
	
	private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

	private LoginType loginType = LoginType.JSON;
		
	private int rememberMeSeconds = 60 * 60 * 24;
	
	public String getLoginPage() {
		return loginPage;	
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}
	
	
	
}
