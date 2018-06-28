/**
 * 
 */
package com.fairy.security;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author Administrator
 *
 */
@RestController
public class DemoController {

	@Autowired
	private ProviderSignInUtils providerSignInUtils;
	
	@GetMapping("hello")
	public String hello(){
		return "hello";
	}
	
	@PostMapping("/user/register")
	public void register(@Valid User user, HttpServletRequest request){
		String userId = user.getUsername();
		providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
	}
}
