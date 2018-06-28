/**
 * 
 */
package com.fairy.security.core.social.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import com.fairy.security.core.properties.SecurityProperties;

/**
 * 开启spring-social功能
 * 配置一些bean(bean的具体作用见方法注释)
 * 提示:用户自主实现ConnectionSignUp接口，可以提供第三方登录时的默认注册方法,进而不必跳转注册页面	
 * @author Administrator
 *
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private SecurityProperties securityProperties;
	@Autowired(required = false)
	private ConnectionSignUp connectionSignUp;
	
	/**
	 * 配置本地用户信息与服务提供商的对应表
	 * 设置为以"fairy_"开头的表名
	 */
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
		repository.setTablePrefix("fairy_");
		if (connectionSignUp != null) {
			//设置第三方登录时，默认注册接口
			repository.setConnectionSignUp(connectionSignUp);
		}
		return repository; 
	}
	/**
	 * 
	 * @Title: fairySpringSocialConfigurer   
	 * @Description: 配置：处理第三方登录url的前缀。默认为"/fairyAuth"  
	 * @param: @return      
	 * @return: SpringSocialConfigurer      
	 * @throws
	 */
	@Bean
	public SpringSocialConfigurer fairySpringSocialConfigurer(){
		String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
		SpringSocialConfigurer configurer = new FairySpringSocialConfigurer(filterProcessesUrl);
		configurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());
		return configurer;
	}
	/**
	 * 
	 * @Title: providerSignInUtils   
	 * @Description: 配置工具,帮助从session中存取social的user信息(实际是对social的connection进行存取)  
	 * @param: @param connectionFactoryLocator
	 * @param: @return      
	 * @return: ProviderSignInUtils      
	 * @throws
	 */
	@Bean
	public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator){
		return new ProviderSignInUtils(connectionFactoryLocator, 
				getUsersConnectionRepository(connectionFactoryLocator));
	}
}
