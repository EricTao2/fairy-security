/**
 * 
 */
package com.fairy.security.core.social.weixin.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

import com.fairy.security.core.social.wechat.api.WeChat;

/**
 * 负责生成QQConnection
 * 由QQAdapter和QQServiceProvider组成
 * 由QQAutoConfig类配置
 * @author Administrator
 *
 */
public class WeChatConnectionFactory extends OAuth2ConnectionFactory<WeChat> {

	/**
	 * @param providerId
	 * @param serviceProvider
	 * @param apiAdapter
	 */
	public WeChatConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new WeChatServiceProvider(appId, appSecret), new WeChatAdapter());
		// TODO Auto-generated constructor stub
	}

}
