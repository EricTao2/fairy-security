/**
 * 
 */
package com.fairy.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author Administrator
 *
 */
public class WeChatProperties extends SocialProperties {

	private String  providerId = "weixin";

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
}
