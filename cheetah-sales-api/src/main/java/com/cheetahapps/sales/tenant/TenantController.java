package com.cheetahapps.sales.tenant;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/tenants")
@Slf4j
@RequiredArgsConstructor
class TenantController {

	private final TenantBusinessDelegate tenantBusinessDelegate;
	
	
	@GetMapping("/findOne")
	public TenantView findByCode(@AuthenticationPrincipal Jwt jwt) {
		
		String code = (String) jwt.getClaims().get("tenantId");
		
	    return tenantBusinessDelegate.findByCode(code);
	}
	
	@PostMapping("/box")
	public TenantView saveBox(@AuthenticationPrincipal Jwt jwt, @Valid @RequestBody BoxSettings boxSettings) {
		log.info("Setting box config - {}", boxSettings);
		String code = (String) jwt.getClaims().get("tenantId");
		// RZlUbWK4YTPsFBmxamkdb71hZbNvVlEW
		return tenantBusinessDelegate.addBoxSettings(code, boxSettings);
		
	}
}
