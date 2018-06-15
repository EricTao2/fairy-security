package com.fairy.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码处理器接口，提供对验证码的创建，校验功能
 * @author Administrator
 *
 */
public interface ValidateCodeProcessor {
	
	String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";
	
	void create(ServletWebRequest request) throws Exception;

	/**   
	 * @Title: validate   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param servletWebRequest      
	 * @return: void      
	 * @throws   
	 */
	void validate(ServletWebRequest servletWebRequest);
}
