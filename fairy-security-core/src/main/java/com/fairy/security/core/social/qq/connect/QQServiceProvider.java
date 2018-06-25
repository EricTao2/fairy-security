/**
 * 
 */
package com.fairy.security.core.social.qq.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;

import com.fairy.security.core.social.qq.api.QQ;
import com.fairy.security.core.social.qq.api.QQImpl;

/**
 * 由OAuth2Operations和Api组成
 * OAuth2Operations负责和服务提供商进行OAuth认证的过程,SpringSocial里的默认实现是OAuth2Template
 * 这里使用修改过默认OAuth2Template类的子类,QOAuth2Template类
 * Api负责从服务提供商获取用户信息，这里使用QQImpl类
 * 该类是QQConnectionFactory的组成部分
 * @author Administrator
 *
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

	final static private String URL_AUTHORIZE ="https://graph.qq.com/oauth2.0/authorize";
	
	final static private String URL_ACCESSTOKEN ="https://graph.qq.com/oauth2.0/token";
	
	private String appId;
	/**
	 * @param oauth2Operations
	 */
	public QQServiceProvider(String appId, String appSecret) {
		super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESSTOKEN));
		this.appId = appId;
	}

	
	@Override
	public QQ getApi(String accessToken) {
		return new QQImpl(accessToken, appId);
	}

}
