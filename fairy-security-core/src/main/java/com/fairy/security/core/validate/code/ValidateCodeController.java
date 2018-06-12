package com.fairy.security.core.validate.code;

import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.fairy.security.core.properties.SecurityProperties;
import com.fairy.security.core.validate.code.image.ImageCode;
import com.fairy.security.core.validate.code.sms.SmsCodeSender;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;

@RestController
public class ValidateCodeController {
	
	
	@Autowired
	private Map<String, ValidateCodeProcessor> validateCodeProcessors;
	
	@GetMapping("/code/{type}")
	public void createImageCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception{
		validateCodeProcessors.get(type + "CodeProcessor").create(new ServletWebRequest(request, response));
	}

}
