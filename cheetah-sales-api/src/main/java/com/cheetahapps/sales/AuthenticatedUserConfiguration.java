package com.cheetahapps.sales;

import java.util.Arrays;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cheetahapps.sales.core.AuthenticatedUser;
import com.cheetahapps.sales.core.AuthenticatedUserFilter;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.target.ThreadLocalTargetSource;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;


@Configuration
public class AuthenticatedUserConfiguration {
	
	@Bean
	public AuthenticatedUserFilter tenantFilter() {
		return new AuthenticatedUserFilter();
	}

	@Bean
	public FilterRegistrationBean<AuthenticatedUserFilter> tenantFilterRegistration() {
	    FilterRegistrationBean<AuthenticatedUserFilter> result = new FilterRegistrationBean<>();
	    result.setFilter(this.tenantFilter());
	    
	    result.setUrlPatterns(Arrays.asList("/*"));
	    result.setName("Tenant Store Filter");
	    result.setOrder(1);
	    return result;
	}
	
	
	@Bean(destroyMethod = "destroy")
	public ThreadLocalTargetSource threadLocalTenantStore() {
		ThreadLocalTargetSource result = new ThreadLocalTargetSource();
		result.setTargetBeanName("tenantStore");
		return result;
	}

	@Primary
	@Bean(name = "proxiedThreadLocalTargetSource")
	public ProxyFactoryBean proxiedThreadLocalTargetSource(ThreadLocalTargetSource threadLocalTargetSource) {
		ProxyFactoryBean result = new ProxyFactoryBean();
		result.setTargetSource(threadLocalTargetSource);
		return result;
	}

	@Bean(name = "tenantStore")
	@Scope(scopeName = "prototype")
	public AuthenticatedUser tenantStore() {
		return new AuthenticatedUser();
	}

}
