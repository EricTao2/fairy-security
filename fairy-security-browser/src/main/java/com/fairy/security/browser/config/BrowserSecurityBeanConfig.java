/**
 * 
 */
package com.fairy.security.browser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import com.fairy.security.browser.authentication.FairyAuthenticationFailureHandler;
import com.fairy.security.browser.authentication.FairyAuthenticationSuccessHandler;
import com.fairy.security.browser.logout.FairyLogoutSuccessHandler;
import com.fairy.security.browser.session.FairyExpiredSessionStrategy;
import com.fairy.security.browser.session.FairyInvalidSessionStrategy;
import com.fairy.security.core.properties.SecurityProperties;

/**
 * 配置session失效，丢失处理的策略的bean
 *
 */
@Configuration
public class BrowserSecurityBeanConfig {

	@Autowired
	private SecurityProperties securityProperties;
	
	@Bean
	@ConditionalOnMissingBean(InvalidSessionStrategy.class)
	public InvalidSessionStrategy invalidSessionStrategy(){
		return new FairyInvalidSessionStrategy(securityProperties.getBrowser().getSession().getSessionInvalidUrl());
	}
	
	@Bean
	@ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
	public SessionInformationExpiredStrategy sessionInformationExpiredStrategy(){
		return new FairyExpiredSessionStrategy(securityProperties.getBrowser().getSession().getSessionInvalidUrl());
	}
	
	@Bean
	@ConditionalOnMissingBean(LogoutSuccessHandler.class)
	public LogoutSuccessHandler logoutSuccessHandler(){
		return new FairyLogoutSuccessHandler(securityProperties.getBrowser().getLogoutUrl());
	}
	
	
	@Bean
	@ConditionalOnMissingBean(SavedRequestAwareAuthenticationSuccessHandler.class)
	public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler(){
		return new FairyAuthenticationSuccessHandler();
	}
	
	@Bean
	@ConditionalOnMissingBean(SimpleUrlAuthenticationFailureHandler.class)
	public SimpleUrlAuthenticationFailureHandler simpleUrlAuthenticationFailureHandler(){
		return new FairyAuthenticationFailureHandler(securityProperties.getBrowser().getLoginUrl());
	}
	

}
