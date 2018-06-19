/**
 * 
 */
package com.fairy.security.core.authentication.common;

/**
 * 常数类
 * @author Administrator
 *
 */
public interface SecurityConstants {

	String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";
	
	String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";
	
	String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";
	
	String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";
	
	String DEFAULT_LOGIN_PAGE_URL = "/default-login.html";
	
	String DEFAULT_FORM_LOGIN_URL = "/authentication/require";
	
	String DEFAULT_FORM_LOGIN_PROCESSING_URL = "/authentication/form";
	
	String DEFAULT_SMS_LOGIN_PROCESSING_URL = "/authentication/phone";
	
	String DEFAULT_SESSION_INVALID_URL = "/fairy-session-invalid.html";
}
