package com.cheetahapps.sales.security;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;


public class SecurityAuditorAware implements AuditorAware<String> {

	public Optional<String> getCurrentAuditor() {

		return Optional.ofNullable(SecurityContextHolder.getContext())
				.map(SecurityContext::getAuthentication)
				.filter(Authentication::isAuthenticated)
				.map(Authentication::getPrincipal)
				.map(jwt -> (String)((Jwt)jwt).getClaims().get("sub"));
	}
}