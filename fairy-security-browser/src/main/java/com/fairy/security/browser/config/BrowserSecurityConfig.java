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
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import com.fairy.security.browser.authentication.FairyAuthenticationFailureHandler;
import com.fairy.security.browser.authentication.FairyAuthenticationSuccessHandler;
import com.fairy.security.browser.session.FairyExpiredSessionStrategy;
import com.fairy.security.core.authentication.common.SecurityConstants;
import com.fairy.security.core.config.AbstractChannelSecurityConfig;
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
	@Autowired
	private InvalidSessionStrategy invalidSessionStrategy;
	@Autowired
	private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;
	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;
	
	
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
			.sessionManagement()
				.invalidSessionStrategy(invalidSessionStrategy)
				.invalidSessionUrl(securityProperties.getBrowser().getSession().getSessionInvalidUrl()) //session过期后跳转url
				.maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions()) //同一账号session的最大数量
				//.maxSessionsPreventsLogin(true) //阻止超过最大登录数之后的登录
				.expiredSessionStrategy(sessionInformationExpiredStrategy) //session丢失后的策略
				.and()
				.and()
			.logout()
				.logoutUrl("/fairy-logout")
				.logoutSuccessHandler(logoutSuccessHandler)
				.deleteCookies("JSESSIONID")
				.and()
			.authorizeRequests()
				.antMatchers(SecurityConstants.DEFAULT_FORM_LOGIN_URL, 
					SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
					securityProperties.getBrowser().getLoginPage(),
					securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
					securityProperties.getBrowser().getLogoutUrl())
			.permitAll()
				.and().authorizeRequests().anyRequest().authenticated()
				.and().csrf().disable();
			
		
	}
}
