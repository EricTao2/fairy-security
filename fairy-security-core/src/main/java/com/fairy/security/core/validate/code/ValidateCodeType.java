/**
 * 
 */
package com.fairy.security.core.validate.code;

import com.fairy.security.core.authentication.common.SecurityCoreConstants;

/**
 * 验证码类型枚举
 * @author Administrator
 *
 */
public enum ValidateCodeType {

	SMS {
		@Override
		public String getParamNameOnValidate() {
			return SecurityCoreConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
		}
	},
	
	IMAGE {
		@Override
		public String getParamNameOnValidate() {
			return SecurityCoreConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
		}
	};
	
	public abstract String getParamNameOnValidate();
}
