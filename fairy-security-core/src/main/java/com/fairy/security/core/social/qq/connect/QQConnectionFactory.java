/**
 * 
 */
package com.fairy.security.core.social.qq.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

import com.fairy.security.core.social.qq.api.QQ;

/**
 * 负责生成QQConnection
 * 由QQAdapter和QQServiceProvider组成
 * 由QQAutoConfig类配置
 * @author Administrator
 *
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

	/**
	 * @param providerId
	 * @param serviceProvider
	 * @param apiAdapter
	 */
	public QQConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
		// TODO Auto-generated constructor stub
	}

}
