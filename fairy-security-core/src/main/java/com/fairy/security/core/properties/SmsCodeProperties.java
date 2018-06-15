package com.fairy.security.core.properties;

import com.fairy.security.core.authentication.common.SecurityConstants;

public class SmsCodeProperties {
	private int length = 4;
	
	private int expireIn = 600;
	
	private String url = SecurityConstants.DEFAULT_SMS_LOGIN_PROCESSING_URL;



	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getExpireIn() {
		return expireIn;
	}

	public void setExpireIn(int expireIn) {
		this.expireIn = expireIn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}




}
