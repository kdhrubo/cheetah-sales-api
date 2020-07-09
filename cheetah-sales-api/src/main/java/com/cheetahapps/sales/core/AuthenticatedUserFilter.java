package com.cheetahapps.sales.core;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticatedUserFilter implements Filter {

	@Autowired
	private AuthenticatedUser user;

	

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// NOOP
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

	    // LOGGER.info("Thread had tenant data: {} from an old request", this.tenantStore.getTenantId());

	    
		try {
			
			String tenantId = (String) RequestContextHolder.getRequestAttributes().getAttribute("tenantId",  RequestAttributes.SCOPE_REQUEST);
			String tenantCode = (String)RequestContextHolder.getRequestAttributes().getAttribute("tenantCode", RequestAttributes.SCOPE_REQUEST);
			String userId = (String)RequestContextHolder.getRequestAttributes().getAttribute("userId", RequestAttributes.SCOPE_REQUEST);
			String email = (String)RequestContextHolder.getRequestAttributes().getAttribute("email", RequestAttributes.SCOPE_REQUEST);
			String firstName = (String)RequestContextHolder.getRequestAttributes().getAttribute("firstName",
					RequestAttributes.SCOPE_REQUEST);
			String lastName = (String)RequestContextHolder.getRequestAttributes().getAttribute("lastName", RequestAttributes.SCOPE_REQUEST);
			
			this.user.setFirstName(firstName);
			this.user.setUserId(userId);
			this.user.setLastName(lastName);
			this.user.setEmail(email);
			this.user.setTenantCode(tenantCode);
			this.user.setTenantId(tenantId);
			
			
			chain.doFilter(servletRequest, servletResponse);
		} finally {
		    
		    this.user.clear();
		}
	}

	@Override
	public void destroy() {
		// NOOP
	}

}