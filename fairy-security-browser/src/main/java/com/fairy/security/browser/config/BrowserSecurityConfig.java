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

import com.fairy.security.browser.authentication.FairyAuthenticationFailureHandler;
import com.fairy.security.browser.authentication.FairyAuthenticationSuccessHandler;
import com.fairy.security.core.config.SmsCodeAuthenticationSecurityConfig;
import com.fairy.security.core.properties.SecurityProperties;
import com.fairy.security.core.validate.code.SmsCodeFilter;
import com.fairy.security.core.validate.code.ValidateCodeFilter;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;
	@Autowired
	private FairyAuthenticationSuccessHandler fairyAuthenticationSuccessHandler;
	@Autowired
	private FairyAuthenticationFailureHandler fairyAuthenticationFailureHandler;
	@Autowired
	private ValidateCodeFilter validateCodeFilter;
	@Autowired
	private SmsCodeFilter smsCodeFilter;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl = new JdbcTokenRepositoryImpl();
		jdbcTokenRepositoryImpl.setDataSource(dataSource);
//		jdbcTokenRepositoryImpl.setCreateTableOnStartup(true);
		return jdbcTokenRepositoryImpl;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		validateCodeFilter.setAuthenticationFailureHandler(fairyAuthenticationFailureHandler);
		
		http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
			.formLogin()
				.loginPage("/authentication/require")
				.loginProcessingUrl("/authentication/form")
				.successHandler(fairyAuthenticationSuccessHandler)
				.failureHandler(fairyAuthenticationFailureHandler)
				.and()
			.rememberMe()
				.tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
				.userDetailsService(userDetailsService)
				.and()
			.authorizeRequests()
			.antMatchers("/authentication/require", 
					securityProperties.getBrowser().getLoginPage(),
					"/code/*")
			.permitAll()
			.and().authorizeRequests().anyRequest().authenticated()
			.and().csrf().disable()
			.apply(smsCodeAuthenticationSecurityConfig);
		
	}
}
