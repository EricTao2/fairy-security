package com.fairy.security.core.properties;

public class SmsCodeProperties {
	private int length = 4;
	
	private int expireIn = 600;
	
	private String url = "/authentication/phone";



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
