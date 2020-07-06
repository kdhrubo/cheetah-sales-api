package com.cheetahapps.sales.security;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtException;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class MultiTenantNimbusJwtDecoder implements JwtDecoder {
	
	private JwtDecoder decoder;
	
	private MultiTenantNimbusJwtDecoder(JwtDecoder decoder) {
		this.decoder = decoder;
	}
	
	public static MultiTenantNimbusJwtDecoder create(String issuer) {
		return new MultiTenantNimbusJwtDecoder(JwtDecoders.fromIssuerLocation(issuer));
	}
	

	@Override
	public Jwt decode(String token) throws JwtException {
		log.info("## Multi tenant decoder called ##");
		Jwt jwt = decoder.decode(token);
		String tenantId = (String) jwt.getClaims().get("tenantId");
		String tenantCode = (String) jwt.getClaims().get("tenantCode");
		String userId = (String) jwt.getClaims().get("userId");
		String sub = (String) jwt.getClaims().get("sub");
		
		RequestContextHolder.getRequestAttributes().setAttribute("tenantId", tenantId, RequestAttributes.SCOPE_REQUEST);
		RequestContextHolder.getRequestAttributes().setAttribute("tenantCode", tenantCode, RequestAttributes.SCOPE_REQUEST);
		RequestContextHolder.getRequestAttributes().setAttribute("userId", userId, RequestAttributes.SCOPE_REQUEST);
		RequestContextHolder.getRequestAttributes().setAttribute("sub", sub, RequestAttributes.SCOPE_REQUEST);
		
		return decoder.decode(token);
	}

}
