package com.fairy.security.core.validate.code;

import java.io.IOException;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeProcessor {
	
	String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";
	
	void create(ServletWebRequest request) throws Exception;
}
