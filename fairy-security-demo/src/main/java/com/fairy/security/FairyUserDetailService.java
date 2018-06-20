package com.fairy.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class FairyUserDetailService implements UserDetailsService, SocialUserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	private Logger logger = LoggerFactory.getLogger(FairyUserDetailService.class);
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		logger.info("表单登录用户名：" + userName);
		return buildUser(userName);
	}

	
	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		logger.info("表单登录用户Id：" + userId);
		return buildUser(userId);
	}
	
	private SocialUserDetails buildUser(String userName) {
		String password = passwordEncoder.encode("123123");
		SocialUser user = new SocialUser(userName, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
		return user;
	}

}
