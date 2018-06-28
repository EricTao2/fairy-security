/**
 * 
 */
package com.fairy.security.core.social.wechat.api;

import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * @author Administrator
 *
 */
public class WeiXinImpl extends AbstractOAuth2ApiBinding implements WeChat {

	final static private String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
	
	final static private String URL_GET_USER_INFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
	
	
	private String appId;
	
	private String openId;
	
	public WeiXinImpl(String accessToken, String appId) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		
		this.appId = appId;
		
		String url = String.format(URL_GET_OPENID, accessToken);
		String result = getRestTemplate().getForObject(url, String.class);
		
		
		this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
	}
	
	/* (non-Javadoc)
	 * @see com.fairy.security.core.social.weixin.api.WeiXin#getUserInfo()
	 */
	@Override
	public WeChatUserInfo getUserInfo() {
		
		return null;
	}

}
