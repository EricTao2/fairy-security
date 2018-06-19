/**
 * 
 */
package com.fairy.security.browser.session;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 * session的丢失处理策略
 * @author Administrator
 *
 */
public class FairyExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {

	
	/**
	 * @param invalidSessionUrl
	 */
	public FairyExpiredSessionStrategy(String invalidSessionUrl) {
		super(invalidSessionUrl);
	}

	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		onSessionInvalid(event.getRequest(), event.getResponse());
	}
	
	@Override
	protected boolean isConcurrency() {
		return true;
	}

}
