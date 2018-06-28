/**
 * 
 */
package com.fairy.security.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

import com.fairy.security.core.authentication.common.SecurityCoreConstants;
import com.fairy.security.core.properties.SecurityProperties;
import com.fairy.security.core.validate.code.config.ValidateCodeSecurityConfig;
import com.fairy.security.core.validate.code.sms.authentication.SmsCodeAuthenticationSecurityConfig;

/**
 * @author Administrator
 *
 */
@Configuration
@EnableResourceServer
public class FairyResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	protected AuthenticationSuccessHandler fairyAuthenticationSuccessHandler;
	
	@Autowired
	protected AuthenticationFailureHandler fairyAuthenticationFailureHandler;
	
	@Autowired
	private SecurityProperties securityProperties;
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;
	@Autowired
	private SpringSocialConfigurer fairySocialConfig;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.formLogin()
		.loginPage(SecurityCoreConstants.DEFAULT_FORM_LOGIN_URL)
		.loginProcessingUrl(SecurityCoreConstants.DEFAULT_FORM_LOGIN_PROCESSING_URL)
		.successHandler(fairyAuthenticationSuccessHandler)
		.failureHandler(fairyAuthenticationFailureHandler);
		
		http//.apply(validateCodeSecurityConfig)
			//	.and()
			.apply(smsCodeAuthenticationSecurityConfig)
				.and()
			.apply(fairySocialConfig)
				.and()
			 .authorizeRequests()
				.antMatchers(SecurityCoreConstants.DEFAULT_FORM_LOGIN_URL, 
					SecurityCoreConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
					securityProperties.getBrowser().getLoginUrl(),
					securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
					securityProperties.getBrowser().getLogoutSuccessUrl())
			.permitAll()
				.and().authorizeRequests().anyRequest().authenticated()
				.and().csrf().disable();
			
		
	}
}
