/**
 * 
 */
package com.fairy.security.core.validate.code.config;



import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

/**
 * 构建验证码过滤器并加入过滤器链
 * @author Administrator
 *
 */
@Component
public class ValidateCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	@Autowired
	private Filter validateCodeFilter;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(validateCodeFilter, AbstractPreAuthenticatedProcessingFilter.class);
	}
}
