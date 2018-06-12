package com.fairy.security.core.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.fairy.security.core.properties.SecurityProperties;

@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
		
}
