/**
 * 
 */
package com.fairy.security.core.social.support;

/**
 * @author Administrator
 *
 */
public class SocialUserInfo {
	
	/**
	 * 应用id 
	 */
	private String providerId;
	/**
	 * 应用中的userId
	 */
	private String providerUesrId;
	/**
	 * 用户昵称
	 */
	private String nickname;
	/**
	 * 用户头像
	 */
	private String avatar;
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public String getProviderUesrId() {
		return providerUesrId;
	}
	public void setProviderUesrId(String providerUesrId) {
		this.providerUesrId = providerUesrId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	
}
