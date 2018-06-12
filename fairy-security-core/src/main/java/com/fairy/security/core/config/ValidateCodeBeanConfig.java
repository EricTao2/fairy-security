package com.fairy.security.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fairy.security.core.properties.SecurityProperties;
import com.fairy.security.core.validate.code.ValidateCodeGenerator;
import com.fairy.security.core.validate.code.image.ImageCodeGenerator;
import com.fairy.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.fairy.security.core.validate.code.sms.SmsCodeSender;

@Configuration
public class ValidateCodeBeanConfig {

	@Autowired
	private SecurityProperties securityProperties;
	
	@Bean
	@ConditionalOnMissingBean(name = "imageCodeGenerator")
	public ValidateCodeGenerator imageCodeGenerator(){
		ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
		imageCodeGenerator.setSecurityProperties(securityProperties);
		return imageCodeGenerator;
	}
	
	@Bean
	@ConditionalOnMissingBean(name = "smsCodeGenerator")
	public ValidateCodeGenerator smsCodeGenerator(){
		ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
		imageCodeGenerator.setSecurityProperties(securityProperties);
		return imageCodeGenerator;
	}
	
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender(){
		SmsCodeSender smsCodeSender = new DefaultSmsCodeSender();
		return smsCodeSender;
	}
	
	
}
