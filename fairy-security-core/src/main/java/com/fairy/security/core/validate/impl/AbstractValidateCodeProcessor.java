package com.fairy.security.core.validate.impl;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import com.fairy.security.core.validate.code.ValidateCode;
import com.fairy.security.core.validate.code.ValidateCodeGenerator;
import com.fairy.security.core.validate.code.ValidateCodeProcessor;

public abstract class AbstractValidateCodeProcessor<T extends ValidateCode> implements ValidateCodeProcessor {

	@Autowired
	private SessionStrategy sessionStrategy;
	@Autowired
	private Map<String, ValidateCodeGenerator> validateCodeGenerators;
	
	@Override
	public void create(ServletWebRequest request) throws Exception {
		T validateCode = generate(request);
		save(request, validateCode);
		send(request, validateCode);
		
	}

	/**
	 * 
	 * @Title: generate   
	 * @Description: 生成并返回验证码   
	 * @param: @param request
	 * @param: @return      
	 * @return: T      
	 * @throws
	 */
	private T generate(ServletWebRequest request) {
		String codeType = getProcessorType(request);
		ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(codeType + "CodeGenerator");
		return (T) validateCodeGenerator.generate(request);
	}

	/**
	 * 
	 * @Title: getProcessorType   
	 * @Description: 获取processor的类型   
	 * @param: @param request
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	private String getProcessorType(ServletWebRequest request) {
		return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
	}

	/**
	 * 
	 * @Title: save   
	 * @Description: 将验证码存入session  
	 * @param: @param request
	 * @param: @param validateCode      
	 * @return: void      
	 * @throws
	 */
	private void save(ServletWebRequest request, T validateCode) {
		sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX + getProcessorType(request).toUpperCase(), validateCode.getCode());
	}
	
	abstract protected void send(ServletWebRequest request, T validateCode) throws Exception;

}
