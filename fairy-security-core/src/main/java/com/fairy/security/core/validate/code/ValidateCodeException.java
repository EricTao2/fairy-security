package com.fairy.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2255241787695851461L;

	public ValidateCodeException(String msg) {
		super(msg);
	}

}
