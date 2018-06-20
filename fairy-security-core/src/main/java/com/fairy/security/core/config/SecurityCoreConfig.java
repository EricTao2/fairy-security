package com.fairy.security.core.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fairy.security.core.properties.SecurityProperties;

/**
 * 注入properties配置
 * @author Administrator
 *
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
		
	/**
	 * 
	 * @Title: passwordEncoder   
	 * @Description: 默认的加密编码类
	 * @param: @return      
	 * @return: PasswordEncoder      
	 * @throws
	 */
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
