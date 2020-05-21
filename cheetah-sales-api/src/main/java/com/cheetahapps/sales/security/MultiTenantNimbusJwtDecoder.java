package com.cheetahapps.sales.security;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class MultiTenantNimbusJwtDecoder implements JwtDecoder {
	
	private JwtDecoder decoder;
	
	private MultiTenantNimbusJwtDecoder(JwtDecoder decoder) {
		this.decoder = decoder;
	}
	
	
	public static MultiTenantNimbusJwtDecoder withJwkSetUri(String jwkSetUri) {
		log.info("*** Creating Multi tenant decoder ***");
		JwtDecoder ldecoder = NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
		return new MultiTenantNimbusJwtDecoder(ldecoder);
	}

	@Override
	public Jwt decode(String token) throws JwtException {
		log.info("## Multi tenant decoder called ##");
		Jwt jwt = decoder.decode(token);
		String tenantId = (String) jwt.getClaims().get("tenantId");
		
		log.info("Tenant Id - {}", tenantId);
		
		RequestContextHolder.getRequestAttributes().setAttribute("tenantId", tenantId, RequestAttributes.SCOPE_REQUEST);
		
		
		return decoder.decode(token);
	}

}
