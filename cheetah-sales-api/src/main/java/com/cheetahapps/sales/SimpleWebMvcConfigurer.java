package com.cheetahapps.sales;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cheetahapps.sales.web.interceptor.TenantHandlerInterceptorAdapter;

@Configuration
public class SimpleWebMvcConfigurer implements  WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		WebMvcConfigurer.super.addInterceptors(registry);
		
		registry.addInterceptor(new TenantHandlerInterceptorAdapter());
	}
	
	
}
