/**
 * 
 */
package com.fairy.security.browser.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 * session的失效处理策略
 * @author Administrator
 *
 */
public class FairyInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {

	/**
	 * @param invalidSessionUrl
	 */
	public FairyInvalidSessionStrategy(String invalidSessionUrl) {
		super(invalidSessionUrl);
	}


	@Override
	public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		onSessionInvalid(request, response);
		
	}

	


}
