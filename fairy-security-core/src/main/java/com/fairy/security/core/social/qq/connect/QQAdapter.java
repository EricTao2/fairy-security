/**
 * 
 */
package com.fairy.security.core.social.qq.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.fairy.security.core.social.qq.api.QQ;
import com.fairy.security.core.social.qq.api.QQUserInfo;

/**
 * @author Administrator
 *
 */
public class QQAdapter implements ApiAdapter<QQ> {

	/* (non-Javadoc)
	 * @see org.springframework.social.connect.ApiAdapter#test(java.lang.Object)
	 */
	@Override
	public boolean test(QQ api) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.social.connect.ApiAdapter#setConnectionValues(java.lang.Object, org.springframework.social.connect.ConnectionValues)
	 */
	@Override
	public void setConnectionValues (QQ api, ConnectionValues values) {
			QQUserInfo userInfo = api.getUserInfo();
			values.setDisplayName(userInfo.getNickname());
			values.setImageUrl(userInfo.getFigureurl_qq_1());
			values.setProfileUrl(null);
			values.setProviderUserId(userInfo.getOpenId());
	}

	/* (non-Javadoc)
	 * @see org.springframework.social.connect.ApiAdapter#fetchUserProfile(java.lang.Object)
	 */
	@Override
	public UserProfile fetchUserProfile(QQ api) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.social.connect.ApiAdapter#updateStatus(java.lang.Object, java.lang.String)
	 */
	@Override
	public void updateStatus(QQ api, String message) {
		// do nothing
		
	}

	
}
