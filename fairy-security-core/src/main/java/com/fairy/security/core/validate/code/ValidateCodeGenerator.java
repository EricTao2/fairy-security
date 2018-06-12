package com.fairy.security.core.validate.code;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
@Component
public interface ValidateCodeGenerator {

	ValidateCode generate(ServletWebRequest request);
}
