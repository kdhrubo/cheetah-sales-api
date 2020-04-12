package com.cheetahapps.sales.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TenantHandlerInterceptorAdapter extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest requestServlet, HttpServletResponse responseServlet, Object handler)
			throws Exception {
		log.info("MINIMAL: INTERCEPTOR PREHANDLE CALLED");

		String tenantId = requestServlet.getHeader("X-TENANT-ID");

		log.info("Tenant id - {}", tenantId);

		// check if not found get it by parsing JWT Token

		

		RequestContextHolder.getRequestAttributes().setAttribute("tenantId", tenantId, RequestAttributes.SCOPE_REQUEST);

		return true;
	}

	

}
