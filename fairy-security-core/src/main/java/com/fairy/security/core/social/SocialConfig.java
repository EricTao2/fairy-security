/**
 * 
 */
package com.fairy.security.core.social;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import com.fairy.security.core.properties.SecurityProperties;

/**
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
	
	/**
	 * 配置本地用户信息与服务提供商的对应表
	 * 设置为以"fairy_"开头的表名
	 */
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
		repository.setTablePrefix("fairy_");
		return repository; 
	}
	/**
	 * 
	 * @Title: fairySpringSocialConfigurer   
	 * @Description: 配置：处理第三方登录url的前缀。默认为"/auth"  
	 * @param: @return      
	 * @return: SpringSocialConfigurer      
	 * @throws
	 */
	@Bean
	public SpringSocialConfigurer fairySpringSocialConfigurer(){
		String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
		SpringSocialConfigurer configurer = new FairySpringSocialConfigurer(filterProcessesUrl);
		return configurer;
	}
}
