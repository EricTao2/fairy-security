package com.fairy.security.core.validate.code.sms;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.fairy.security.core.properties.SecurityProperties;
import com.fairy.security.core.validate.code.ValidateCode;
import com.fairy.security.core.validate.code.ValidateCodeGenerator;
@Component
public class SmsCodeGenerator implements ValidateCodeGenerator {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	public ValidateCode generate(ServletWebRequest request) {
		String asd = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
		ValidateCode smsCode = new ValidateCode(RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength())
				, securityProperties.getCode().getSms().getExpireIn());
		return smsCode;
	}

}
