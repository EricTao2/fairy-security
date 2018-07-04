/**
 * 
 */
package com.fairy.security.core.social.wechat.connect;

import org.springframework.social.oauth2.AccessGrant;

/**
 * @author Administrator
 *
 */
public class WeChatAccessGrant extends AccessGrant {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
