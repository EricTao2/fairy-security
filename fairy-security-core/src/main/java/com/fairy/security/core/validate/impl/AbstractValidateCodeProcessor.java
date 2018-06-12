package com.fairy.security.core.validate.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.fairy.security.core.validate.code.ValidateCode;
import com.fairy.security.core.validate.code.ValidateCodeException;
import com.fairy.security.core.validate.code.ValidateCodeGenerator;
import com.fairy.security.core.validate.code.ValidateCodeProcessor;
import com.fairy.security.core.validate.code.ValidateCodeType;
import com.fairy.security.core.validate.code.image.ImageCode;

public abstract class AbstractValidateCodeProcessor<T extends ValidateCode> implements ValidateCodeProcessor {

	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
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
	@SuppressWarnings("unchecked")
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
		sessionStrategy.setAttribute(request, getCodeSessionKey(), validateCode);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void validate(ServletWebRequest request) {
		ValidateCodeType validateCodeType = getValidateCodeType(request);
		String codeSessionKey = getCodeSessionKey();
		T codeInSession = (T)sessionStrategy.getAttribute(request, codeSessionKey);
		String codeInRequest = null;
		try {
			codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), validateCodeType.getParamNameOnValidate());
		} catch (ServletRequestBindingException e) {
			throw new ValidateCodeException("服务器获取验证码失败");
		}
		if (StringUtils.isBlank(codeInRequest)) {
			throw new ValidateCodeException("验证码不能为空");
		}
		if (codeInSession == null) {
			throw new ValidateCodeException("验证码失效");
		}
		if (codeInSession.isExpired()) {
			throw new ValidateCodeException("验证码已过期，请重新验证");
		}
		if (!StringUtils.equalsIgnoreCase(codeInSession.getCode(), codeInRequest)) {
			throw new ValidateCodeException("验证码输入错误");
		}
		sessionStrategy.removeAttribute(request, codeSessionKey);
	}
	
	private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
		String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
		return ValidateCodeType.valueOf(type.toUpperCase());
	}

	private String getCodeSessionKey() {
		String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
		return SESSION_KEY_PREFIX + type.toUpperCase();
	}

	abstract protected void send(ServletWebRequest request, T validateCode) throws Exception;
	
}
