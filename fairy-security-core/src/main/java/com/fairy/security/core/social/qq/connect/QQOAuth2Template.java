/**
 * 
 */
package com.fairy.security.core.social.qq.connect;

import java.nio.charset.Charset;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fairy.security.core.properties.SecurityProperties;

/**
 * 对SpringSocial的OAuth2Template类进行修改
 * 1.解析http的content-type不支持text/html,所以加入StringHttpMessageConverter类(默认是支持的json)
 * 2.返回结果会当做json格式来解析，修改为qq互联提供的格式解析
 * @author Administrator
 *
 */
public class QQOAuth2Template extends OAuth2Template {
	
	

	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * @param clientId
	 * @param clientSecret
	 * @param authorizeUrl
	 * @param accessTokenUrl
	 */
	public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
		super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
		setUseParametersForClientAuthentication(true);
	}


	/* (non-Javadoc)
	 * @see org.springframework.social.oauth2.OAuth2Template#createRestTemplate()
	 */
	@Override
	protected RestTemplate createRestTemplate() {
		RestTemplate restTemplate = super.createRestTemplate();
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return restTemplate;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.social.oauth2.OAuth2Template#postForAccessGrant(java.lang.String, org.springframework.util.MultiValueMap)
	 */
	@Override
	protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
		String response = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
		logger.info("获取accessToken的响应结果:" + response	);
		
		String[] items = StringUtils.splitByWholeSeparator(response, "&");
		
		String  accessToken = StringUtils.substringAfterLast(items[0], "=");
		Long  expiresIn = new Long(StringUtils.substringAfterLast(items[1], "="));
		String  refreshToken = StringUtils.substringAfterLast(items[2], "=");
		return new AccessGrant(accessToken, null, refreshToken, expiresIn);
	}
	
	
}
