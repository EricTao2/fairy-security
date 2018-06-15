/**
 * 
 */
package com.fairy.security.core.validate.code;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 验证码处理器持有类，持有所有验证码处理器
 * @author Administrator
 *
 */
@Component
public class ValidateCodeProcessorHolder {

	@Autowired
	private Map<String, ValidateCodeProcessor> validateCodeProcessors;

	/**   
	 * @Title: findValidateCodeProcessor   
	 * @Description: 找到对应验证码类型的验证码处理器   
	 * @param: @param type
	 * @param: @return      
	 * @return: ValidateCodeFilter      
	 * @throws   
	 */
	public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
		return validateCodeProcessors.get(type.toString().toLowerCase() + "CodeProcessor");
		
	}
	
	
}
