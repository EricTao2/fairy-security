/**
 * 
 */
package com.fairy.security.core.social.weixin.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import com.fairy.security.core.social.wechat.api.WeChat;
import com.fairy.security.core.social.wechat.api.WeiXinImpl;

/**
 * @author Administrator
 *
 */
public class WeChatServiceProvider extends AbstractOAuth2ServiceProvider<WeChat> {

	final static private String URL_AUTHORIZE ="https://open.weixin.qq.com/connect/qrconnect";
	
	final static private String URL_ACCESSTOKEN ="https://api.weixin.qq.com/sns/oauth2/access_token";
	
	private String appId;
	
	/**
	 * @param oauth2Operations
	 */
	public WeChatServiceProvider(String appId, String appSecret) {
		super(new WeChatOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESSTOKEN));
		this.appId = appId;
	}

	/* (non-Javadoc)
	 * @see org.springframework.social.oauth2.AbstractOAuth2ServiceProvider#getApi(java.lang.String)
	 */
	@Override
	public WeChat getApi(String accessToken) {
		return new WeiXinImpl(accessToken, appId);
	}

}
