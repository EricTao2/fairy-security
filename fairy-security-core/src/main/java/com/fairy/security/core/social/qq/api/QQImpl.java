/**
 * 
 */
package com.fairy.security.core.social.qq.api;

import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * QQ接口的实现，实现了从qq互联服务提供商获取qq用户信息
 * @author Administrator
 *
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {
	
	final static private String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
	
	final static private String URL_GET_USER_INFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
	
	private String appId;
	
	private String openId;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	
	public QQImpl(String accessToken, String appId) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		
		this.appId = appId;
		
		String url = String.format(URL_GET_OPENID, accessToken);
		String result = getRestTemplate().getForObject(url, String.class);
		
		
		this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
	}

	@Override
	public QQUserInfo getUserInfo(){
		String url = String.format(URL_GET_USER_INFO, appId, openId);
		
		String result = getRestTemplate().getForObject(url, String.class);
		
		try {
			QQUserInfo userInfo = objectMapper.readValue(result, QQUserInfo.class);
			userInfo.setOpenId(openId);
			return userInfo;
		} catch (Exception e) {
			throw new RuntimeException("获取用户信息失败");
		}
	}

}
