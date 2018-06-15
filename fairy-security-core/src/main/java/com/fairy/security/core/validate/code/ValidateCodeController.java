package com.fairy.security.core.validate.code;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 根据需要的验证码类型，调用验证码处理器生成对应验证码
 * @author Administrator
 *
 */
@RestController
public class ValidateCodeController {
	
	
	@Autowired
	private Map<String, ValidateCodeProcessor> validateCodeProcessors;
	
	@GetMapping("/code/{type}")
	public void createImageCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception{
		validateCodeProcessors.get(type + "CodeProcessor").create(new ServletWebRequest(request, response));
	}

}
