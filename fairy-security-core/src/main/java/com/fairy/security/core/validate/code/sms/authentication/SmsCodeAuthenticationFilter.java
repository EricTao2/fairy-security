/**
 * 
 */
package com.fairy.security.core.validate.code.sms.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import com.fairy.security.core.authentication.common.SecurityCoreConstants;

/**
 * SMS登录验证的Filter，并由SmsCodeAuthenticationSecurityConfig类配置
 * @author Administrator
 *
 */
public class SmsCodeAuthenticationFilter  extends AbstractAuthenticationProcessingFilter {
	// ~ Static fields/initializers
		// =====================================================================================

		public static final String FAIRY_SECURITY_FORM_PHONE_KEY = "phone";

		private String phoneParameter = FAIRY_SECURITY_FORM_PHONE_KEY;
		private boolean postOnly = true;

		// ~ Constructors
		// ===================================================================================================

		public SmsCodeAuthenticationFilter() {
			super(new AntPathRequestMatcher(SecurityCoreConstants.DEFAULT_SMS_LOGIN_PROCESSING_URL, "POST"));
		}

		// ~ Methods
		// ========================================================================================================

		public Authentication attemptAuthentication(HttpServletRequest request,
				HttpServletResponse response) throws AuthenticationException {
			if (postOnly && !request.getMethod().equals("POST")) {
				throw new AuthenticationServiceException(
						"Authentication method not supported: " + request.getMethod());
			}

			String phone = obtainPhone(request);

			if (phone == null) {
				phone = "";
			}

			phone = phone.trim();

			SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(phone);

			// Allow subclasses to set the "details" property
			setDetails(request, authRequest);

			return this.getAuthenticationManager().authenticate(authRequest);
		}

		

		
		protected String obtainPhone(HttpServletRequest request) {
			return request.getParameter(phoneParameter);
		}

		protected void setDetails(HttpServletRequest request,
				SmsCodeAuthenticationToken authRequest) {
			authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
		}

		public void setPhoneParameter(String phoneParameter) {
			Assert.hasText(phoneParameter, "Username parameter must not be empty or null");
			this.phoneParameter = phoneParameter;
		}


		public void setPostOnly(boolean postOnly) {
			this.postOnly = postOnly;
		}

		public final String getPhoneParameter() {
			return phoneParameter;
		}
}
