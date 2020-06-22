package com.cheetahapps.sales.tenant;


import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheetahapps.sales.core.AbstractController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/tenants")
@Slf4j
class TenantController extends AbstractController<Tenant, String>{

	private final TenantBusinessDelegate tenantBusinessDelegate;

	
	public TenantController(TenantBusinessDelegate businessDelegate) {
		super(businessDelegate);
		this.tenantBusinessDelegate = businessDelegate;
	}
	
	
	@GetMapping("/findOne")
	public TenantView findByCode(@AuthenticationPrincipal Jwt jwt) {
		
		String code = (String) jwt.getClaims().get("tenantId");
		
	    return tenantBusinessDelegate.findByCode(code);
	}
	
	@PostMapping("/provision")
	public TenantView completeProvisioning(
			@AuthenticationPrincipal Jwt jwt,
			@RequestBody @Valid TenantPreference tenantPreference) {
		
		log.info("tenantPreference - {}", tenantPreference);
		
		return null;
	}
}
