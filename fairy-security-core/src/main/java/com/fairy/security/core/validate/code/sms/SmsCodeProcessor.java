/**
 * 
 */
package com.fairy.security.core.validate.code.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.fairy.security.core.validate.code.ValidateCode;
import com.fairy.security.core.validate.code.ValidateCodeProcessor;
import com.fairy.security.core.validate.impl.AbstractValidateCodeProcessor;

/**
 * @author Administrator
 *
 */
@Component
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> implements ValidateCodeProcessor {

	@Autowired
	private SmsCodeSender smsCodeSender;

	@Override
	protected void send(ServletWebRequest request, ValidateCode smsCode) throws Exception {
		String paramName = "phone";
		String phone = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
		smsCodeSender.send(phone, smsCode.getCode());
	}

}
