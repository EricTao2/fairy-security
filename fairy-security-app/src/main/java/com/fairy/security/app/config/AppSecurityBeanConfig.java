/**
 * 
 */
package com.fairy.security.app.config;

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

import com.fairy.security.app.authentication.FairyAuthenticationFailureHandler;
import com.fairy.security.app.authentication.FairyAuthenticationSuccessHandler;
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
