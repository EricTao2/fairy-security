/**
 * 
 */
package com.fairy.security.core.social.weixin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

import com.fairy.security.core.properties.SecurityProperties;
import com.fairy.security.core.properties.WeChatProperties;
import com.fairy.security.core.social.weixin.connect.WeChatConnectionFactory;

/**
 * @author Administrator
 *
 */
@Configuration
@ConditionalOnProperty(prefix = "fairy.security.social.wechat", name = "appId")
public class WeChatAutoConfig extends SocialAutoConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		WeChatProperties weChat = securityProperties.getSocial().getWechat();
		return new WeChatConnectionFactory(weChat.getProviderId(), weChat.getAppId(), weChat.getAppSecret());
	}
	
	

}
