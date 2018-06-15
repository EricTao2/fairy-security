package com.fairy.security.core.validate.code;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成器接口
 * @author Administrator
 *
 */
@Component
public interface ValidateCodeGenerator {

	ValidateCode generate(ServletWebRequest request);
}
