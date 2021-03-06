/**
 * 
 */
package com.fairy.security.core.social.qq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import com.fairy.security.core.properties.QQProperties;
import com.fairy.security.core.properties.SecurityProperties;
import com.fairy.security.core.social.qq.connect.QQConnectionFactory;

/**
 * @author Administrator
 *
 */
@Configuration
@ConditionalOnProperty(prefix = "fairy.security.social.qq", name = "appId")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		QQProperties qq = securityProperties.getSocial().getQq();
		QQConnectionFactory connectionFactory = new QQConnectionFactory(qq.getProviderId(), qq.getAppId(), qq.getAppSecret());
		return connectionFactory;
	}
	
	

}
