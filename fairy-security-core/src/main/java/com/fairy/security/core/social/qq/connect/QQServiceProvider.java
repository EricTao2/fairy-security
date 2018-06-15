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
		super(new OAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESSTOKEN));
	}

	
	@Override
	public QQ getApi(String accessToken) {
		return new QQImpl(accessToken, appId);
	}

}
