/**
 * 
 */
package com.fairy.security.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fairy.security.core.authentication.phone.SmsCodeAuthenticationFilter;
import com.fairy.security.core.authentication.phone.SmsCodeAuthenticationProvider;

/**
 * @author Administrator
 *
 */
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
	
	@Autowired
	private AuthenticationSuccessHandler fairyAuthenticationSuccessHandler;
	@Autowired
	private AuthenticationFailureHandler fairyAuthenticationFailureHandler;
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
		smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(fairyAuthenticationSuccessHandler);
		smsCodeAuthenticationFilter.setAuthenticationFailureHandler(fairyAuthenticationFailureHandler);
	
		SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
		smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);
	
		http.authenticationProvider(smsCodeAuthenticationProvider);
	}
}
