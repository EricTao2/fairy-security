package com.fairy.security.browser.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import com.fairy.security.browser.authentication.FairyAuthenticationFailureHandler;
import com.fairy.security.browser.authentication.FairyAuthenticationSuccessHandler;
import com.fairy.security.core.AbstractChannelSecurityConfig;
import com.fairy.security.core.authentication.common.SecurityConstants;
import com.fairy.security.core.properties.SecurityProperties;
import com.fairy.security.core.validate.code.ValidateCodeFilter;
import com.fairy.security.core.validate.code.config.ValidateCodeSecurityConfig;
import com.fairy.security.core.validate.code.sms.SmsCodeAuthenticationSecurityConfig;

@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

	@Autowired
	private SecurityProperties securityProperties;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;
	@Autowired
	private SpringSocialConfigurer fairySocialConfig;
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl = new JdbcTokenRepositoryImpl();
		jdbcTokenRepositoryImpl.setDataSource(dataSource);
//		jdbcTokenRepositoryImpl.setCreateTableOnStartup(true); //创建表
		return jdbcTokenRepositoryImpl;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		applyPasswordAuthenticationConfig(http);
		
		http.apply(smsCodeAuthenticationSecurityConfig)
				.and()
			.apply(validateCodeSecurityConfig)
				.and()
			.apply(fairySocialConfig)
				.and()
			.rememberMe()
				.tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
				.userDetailsService(userDetailsService)
				.and()
			.authorizeRequests()
			.antMatchers(SecurityConstants.DEFAULT_FORM_LOGIN_URL, 
					securityProperties.getBrowser().getLoginPage(),
					SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*")
			.permitAll()
				.and().authorizeRequests().anyRequest().authenticated()
				.and().csrf().disable();
			
		
	}
}
