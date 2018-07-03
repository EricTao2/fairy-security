package com.fairy.security.core.validate.code.sms;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.fairy.security.core.properties.SecurityProperties;
import com.fairy.security.core.validate.code.ValidateCode;
import com.fairy.security.core.validate.code.ValidateCodeGenerator;
/*
 * 该类在ValidateCodeBeanConfig里条件化配置，不需要@Component注解
 */
public class SmsCodeGenerator implements ValidateCodeGenerator {
	
	
	private SecurityProperties securityProperties;
	
	@Override
	public ValidateCode generate(ServletWebRequest request) {
		ValidateCode smsCode = new ValidateCode(RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength())
				, securityProperties.getCode().getSms().getExpireIn());
		return smsCode;
	}

	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}
	

}
