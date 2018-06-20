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
	
	/**
	 * 默认：获取验证码url前缀，使用时如"/code/{type}"，type为自定义验证码类型
	 */
	String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";
	/**
	 * 默认：短信验证码提交时的参数名
	 */
	String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";
	/**
	 * 默认：图形验证码提交时的参数名
	 */
	String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";
	/**
	 * 默认：验证码存储在session中名称的前缀
	 */
	String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";
	
	/**
	 * 默认：登录页面
	 */
	String DEFAULT_LOGIN_PAGE_URL = "/fairy-login.html";
	/**
	 * 默认：退出登录页面
	 */
	String DEFAULT_LOGOUT_PAGE_URL = "/fairy-logout.html";
	/**
	 * 默认：需要登录时跳转的url(该url最后会返回DEFAULT_LOGIN_PAGE_URL页面)
	 */
	String DEFAULT_FORM_LOGIN_URL = "/authentication/require";
	/**
	 * 默认：表单登录提交url
	 */
	String DEFAULT_FORM_LOGIN_PROCESSING_URL = "/authentication/form";
	/**
	 * 默认：短信登录提交url
	 */
	String DEFAULT_SMS_LOGIN_PROCESSING_URL = "/authentication/phone";
	/**
	 * 默认：session过期跳转rul
	 */
	String DEFAULT_SESSION_INVALID_URL = "/fairy-session-invalid.html";
}
