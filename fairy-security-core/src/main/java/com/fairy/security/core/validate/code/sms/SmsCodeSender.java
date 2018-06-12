package com.fairy.security.core.validate.code.sms;

import com.fairy.security.core.validate.code.ValidateCode;

public interface SmsCodeSender {

	void send(String phone, String code);
}
