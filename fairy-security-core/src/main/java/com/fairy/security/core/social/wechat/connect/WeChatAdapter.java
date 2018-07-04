/**
 * 
 */
package com.fairy.security.core.social.wechat.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.fairy.security.core.social.wechat.api.WeChat;
import com.fairy.security.core.social.wechat.api.WeChatUserInfo;

/**
 * QQ适配器，将认证服务器返回的用户信息转化成springsocial里的用户
 * 该类QQConnectionFactory的组成部分
 * @author Administrator
 *
 */
public class WeChatAdapter implements ApiAdapter<WeChat> {

	/* (non-Javadoc)
	 * @see org.springframework.social.connect.ApiAdapter#test(java.lang.Object)
	 */
	@Override
	public boolean test(WeChat api) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.social.connect.ApiAdapter#setConnectionValues(java.lang.Object, org.springframework.social.connect.ConnectionValues)
	 */
	@Override
	public void setConnectionValues (WeChat api, ConnectionValues values) {
		WeChatUserInfo userInfo = api.getUserInfo();
			values.setDisplayName("测试");
			values.setImageUrl(null);
			values.setProfileUrl(null);
			values.setProviderUserId(userInfo.getOpenId());
	}

	/* (non-Javadoc)
	 * @see org.springframework.social.connect.ApiAdapter#fetchUserProfile(java.lang.Object)
	 */
	@Override
	public UserProfile fetchUserProfile(WeChat api) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.social.connect.ApiAdapter#updateStatus(java.lang.Object, java.lang.String)
	 */
	@Override
	public void updateStatus(WeChat api, String message) {
		// do nothing
		
	}


	
}
