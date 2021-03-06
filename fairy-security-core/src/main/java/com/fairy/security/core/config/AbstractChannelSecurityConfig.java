/**
 * 
 */
package com.fairy.security.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fairy.security.core.authentication.common.SecurityCoreConstants;

/**
 * 提供默认的表单登录功能与处理配置。需要配置AuthenticationSuccessHandler
 * 和AuthenticationFailureHandler的成功失败处理类
 * 和{@link SecurityCoreConstants}定义的登录跳转url，登录处理url
 * @author Administrator
 *
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	protected AuthenticationSuccessHandler fairyAuthenticationSuccessHandler;
	
	@Autowired
	protected AuthenticationFailureHandler fairyAuthenticationFailureHandler;
	
	protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
		http.formLogin()
			.loginPage(SecurityCoreConstants.DEFAULT_FORM_LOGIN_URL)
			.loginProcessingUrl(SecurityCoreConstants.DEFAULT_FORM_LOGIN_PROCESSING_URL)
			.successHandler(fairyAuthenticationSuccessHandler)
			.failureHandler(fairyAuthenticationFailureHandler);
	}
}
