package com.fairy.security.core.validate.code.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fairy.security.core.properties.SecurityProperties;
import com.fairy.security.core.validate.code.ValidateCodeGenerator;
import com.fairy.security.core.validate.code.image.ImageCodeGenerator;
import com.fairy.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.fairy.security.core.validate.code.sms.SmsCodeGenerator;
import com.fairy.security.core.validate.code.sms.SmsCodeSender;

/**
 * 配置SMS验证码和Image验证码的Generator和Sender
 * @author Administrator
 *
 */
@Configuration
public class ValidateCodeBeanConfig {

	@Autowired
	private SecurityProperties securityProperties;
	
	@Bean
	@ConditionalOnMissingBean(ImageCodeGenerator.class)
	public ValidateCodeGenerator imageCodeGenerator(){
		ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
		imageCodeGenerator.setSecurityProperties(securityProperties);
		return imageCodeGenerator;
	}
	
	@Bean
	@ConditionalOnMissingBean(SmsCodeGenerator.class)
	public ValidateCodeGenerator smsCodeGenerator(){
		SmsCodeGenerator smsCodeGenerator = new SmsCodeGenerator();
		smsCodeGenerator.setSecurityProperties(securityProperties);
		return smsCodeGenerator;
	}
	
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender(){
		SmsCodeSender smsCodeSender = new DefaultSmsCodeSender();
		return smsCodeSender;
	}
	
	
}
