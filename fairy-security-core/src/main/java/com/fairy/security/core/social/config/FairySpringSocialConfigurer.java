/**
 * 
 */
package com.fairy.security.core.social.config;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * 配置:第三方授权的拦截url前缀
 * @author Administrator
 *
 */
public class FairySpringSocialConfigurer extends SpringSocialConfigurer {

	private String filterProcessesUrl;
	
	
	public FairySpringSocialConfigurer(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}
	@Override
	protected <T> T postProcess(T object) {
		SocialAuthenticationFilter filter = (SocialAuthenticationFilter)super.postProcess(object);
		filter.setFilterProcessesUrl(filterProcessesUrl);
		return super.postProcess(object);
	}
}
