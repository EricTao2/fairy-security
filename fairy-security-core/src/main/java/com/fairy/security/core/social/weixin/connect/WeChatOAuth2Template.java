/**
 * 
 */
package com.fairy.security.core.social.weixin.connect;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author Administrator
 *
 */
public class WeChatOAuth2Template extends OAuth2Template {

	private final String appId;
	
	private final String clientSecret;
	
	private final String accessTokenUrl;
	
	private final String authorizeUrl;
	
	/**
	 * @param clientId
	 * @param clientSecret
	 * @param authorizeUrl
	 * @param accessTokenUrl
	 */
	public WeChatOAuth2Template(String appId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
		super(appId, clientSecret, authorizeUrl, accessTokenUrl);
		this.appId = appId;
		this.clientSecret = clientSecret;
		String clientInfo = "?appid=" + formEncode(appId);
		this.authorizeUrl = authorizeUrl + clientInfo;
		this.accessTokenUrl = accessTokenUrl;
	}

	@Override
	public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri, MultiValueMap<String, String> additionalParameters) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.set("appid", appId);
		params.set("secret", clientSecret);
		params.set("code", authorizationCode);
		params.set("redirect_uri", redirectUri);
		params.set("grant_type", "authorization_code");
		if (additionalParameters != null) {
			params.putAll(additionalParameters);
		}
		return postForAccessGrant(accessTokenUrl, params);
	}
	
	@Override
	public AccessGrant refreshAccess(String refreshToken, MultiValueMap<String, String> additionalParameters) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.set("appid", appId);
		params.set("secret", clientSecret);
		params.set("refresh_token", refreshToken);
		params.set("grant_type", "refresh_token");
		if (additionalParameters != null) {
			params.putAll(additionalParameters);
		}
		return postForAccessGrant(accessTokenUrl, params);
	}
	
	/**
	 * 覆写:生成accessToken(微信的accessToken带有其他参数)
	 */
	@Override
	protected AccessGrant createAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn,
			Map<String, Object> response) {
		if (StringUtils.isNotBlank(MapUtils.getString(response, "errcode"))) {
			String errcode = MapUtils.getString(response, "errcode");
			String errmsg = MapUtils.getString(response, "errmsg");
			throw new RuntimeException("获取access token失败, errcode:"+errcode+", errmsg:"+errmsg);
		}
		WeChatAccessGrant accessGrant = new WeChatAccessGrant(MapUtils.getString(response, "openid"), accessToken, scope, refreshToken, expiresIn);
		return accessGrant;
	}
	
	/**
	 * 覆写:生成引导用户跳转获取code的url(微信互联没有完全遵守oauth2标准,按照微信的标准进行修改)
	 */
	@Override
	public String buildAuthenticateUrl(OAuth2Parameters parameters) {
		String url = super.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, parameters) + "&scope=snsapi_login";
		if (StringUtils.contains(url, "client_id=")) {
			url = StringUtils.replace(url, "client_id=", "appid=");
		}
		return url;
	}
	@Override
	public String buildAuthorizeUrl(OAuth2Parameters parameters) {
		return this.buildAuthenticateUrl(parameters);
	}
	
	/*@Override
	protected RestTemplate createRestTemplate() {
		RestTemplate restTemplate = super.createRestTemplate();
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return restTemplate;
	}*/
	
	private String formEncode(String data) {
		try {
			return URLEncoder.encode(data, "UTF-8");
		}
		catch (UnsupportedEncodingException ex) {
			// should not happen, UTF-8 is always supported
			throw new IllegalStateException(ex);
		}
	}

	
}
