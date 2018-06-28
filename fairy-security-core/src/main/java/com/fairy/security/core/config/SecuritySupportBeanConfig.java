/**
 * 
 */
package com.fairy.security.core.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

/**
 * 配置一些工具类
 * @author Administrator
 *
 */
@Configuration
public class SecuritySupportBeanConfig {

	@Bean
	@ConditionalOnMissingBean(name = "defaultRedirectStrategy")
	public RedirectStrategy defaultRedirectStrategy() {
		return new DefaultRedirectStrategy();
	}
	
	@Bean
	@ConditionalOnMissingBean(name = "httpSessionRequestCache")
	public RequestCache httpSessionRequestCache() {
		return new HttpSessionRequestCache();
	}
}
