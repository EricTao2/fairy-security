package com.fairy.security.core.social.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.view.AbstractView;

/**
 * 配置:加入视图，显示Spring-Social返回的用户账号与第三方账号的绑定关系
 * @author EricTao
 *
 */
public class FairyConnectedView extends AbstractView {

	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String url = request.getRequestURL().toString();
		String providerId = StringUtils.substringAfterLast(url, "/");
		response.setContentType("text/html;charset=UTF-8");
		if (model.get("connections") != null) {
			response.getWriter().write("<h3>绑定" + providerId + "成功</h3>");
		} else {
			response.getWriter().write("<h3>解绑" + providerId + "成功</h3>");
		}
		
		
	}

}
