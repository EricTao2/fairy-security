/**
 * 
 */
package com.fairy.security.core.properties;

import com.fairy.security.core.authentication.common.SecurityCoreConstants;

/**
 * @author Administrator
 *
 */
public class SessionProperties {

	/**
	 * 同一用户最大同时在线数量
	 */
	private int maximumSessions = 1;
	/**
	 * session失效默认跳转url
	 */
	private String sessionInvalidUrl = SecurityCoreConstants.DEFAULT_SESSION_INVALID_URL;
	
	public int getMaximumSessions() {
		return maximumSessions;
	}
	public void setMaximumSessions(int maximumSessions) {
		this.maximumSessions = maximumSessions;
	}
	public String getSessionInvalidUrl() {
		return sessionInvalidUrl;
	}
	public void setSessionInvalidUrl(String sessionInvalidUrl) {
		this.sessionInvalidUrl = sessionInvalidUrl;
	}
	
	
}
