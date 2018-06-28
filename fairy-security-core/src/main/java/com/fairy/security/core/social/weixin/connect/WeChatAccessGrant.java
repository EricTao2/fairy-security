/**
 * 
 */
package com.fairy.security.core.social.weixin.connect;

import org.springframework.social.oauth2.AccessGrant;

/**
 * @author Administrator
 *
 */
public class WeChatAccessGrant extends AccessGrant {

	private final String openId;
	
	/**
	 * @param accessToken
	 * @param scope
	 * @param refreshToken
	 * @param expiresIn
	 */
	public WeChatAccessGrant(String openId, String accessToken, String scope, String refreshToken, Long expiresIn) {
		super(accessToken, scope, refreshToken, expiresIn);
		this.openId =openId;
	}

}
