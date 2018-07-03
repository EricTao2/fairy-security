/**
 * 
 */
package com.fairy.security.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.fairy.security.core.properties.SecurityProperties;

/**
 * 配置session失效，丢失处理的策略的bean
 *
 */
@Configuration
public class AppSecurityBeanConfig {

	@Autowired
	private SecurityProperties securityProperties;
	
	

	

}
