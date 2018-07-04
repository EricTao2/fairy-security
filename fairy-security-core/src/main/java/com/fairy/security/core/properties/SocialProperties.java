/**
 * 
 */
package com.fairy.security.core.properties;

import com.fairy.security.core.authentication.common.SecurityCoreConstants;

/**
 * @author Administrator
 *
 */
public class SocialProperties {
	
	
	private String filterProcessesUrl = SecurityCoreConstants.DEFAULT_SOCIAL_PROCESSING_URL_PREFIX;

	private QQProperties qq = new QQProperties();
	
	private WeChatProperties wechat = new WeChatProperties();

	public QQProperties getQq() {
		return qq;
	}

	public void setQq(QQProperties qq) {
		this.qq = qq;
	}

	public String getFilterProcessesUrl() {
		return filterProcessesUrl;
	}

	public void setFilterProcessesUrl(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}

	public WeChatProperties getWechat() {
		return wechat;
	}

	public void setWechat(WeChatProperties wechat) {
		this.wechat = wechat;
	}


	
	
}
