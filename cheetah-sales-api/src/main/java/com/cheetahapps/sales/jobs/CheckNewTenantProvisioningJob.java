package com.cheetahapps.sales.jobs;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cheetahapps.sales.tenant.Tenant;
import com.cheetahapps.sales.tenant.TenantRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Component
public class CheckNewTenantProvisioningJob {
	
	private final TenantRepository tenantRepository;
	
	@Scheduled(fixedDelay = 5000)
	public void checkAndProvisionUser() {
		log.debug("Looking for unprovisioned tenants");
		
		log.info("Looking for unprovisioned tenants - {}", tenantRepository.findByProvisionedFalse().size());
	}
}
