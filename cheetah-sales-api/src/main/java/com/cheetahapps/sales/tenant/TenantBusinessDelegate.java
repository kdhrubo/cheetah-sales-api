package com.cheetahapps.sales.tenant;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.event.ProvisionTenantEvent;

import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TenantBusinessDelegate extends AbstractBusinessDelegate<Tenant, String> {

	private final TenantRepository tenantRepository;

	public TenantBusinessDelegate(TenantRepository tenantRepository) {
		super(tenantRepository);
		this.tenantRepository = tenantRepository;
	}

	public Option<Tenant> findFirstUnProvisioned() {
		return this.tenantRepository.findFirstByProvisioned(false);
	}

	@Transactional
	public void updateProvisionStatus(Tenant t) {
		t.setProvisioned(true);
		save(t);
	}

	@EventListener
	@Transactional
	public void provision(ProvisionTenantEvent event) {
		log.info("Provisioning new tenant.");
		Tenant t = event.getTenant();
		t.setProvisioned(true);
		save(event.getTenant());
	}
	
	@Transactional(readOnly = true)
	public TenantView findByCode(String code) {
		return tenantRepository.findByCode(code);
	}
	
	@Transactional
	public TenantView addBoxSettings(String code, BoxSettings boxSettings) {
		Option<Tenant> t = this.tenantRepository.findFirstByProvisioned(true);
		
		t.peek( i -> {
			Tenant localTenant = t.get();
			localTenant.setBoxSettings(boxSettings);
			save(localTenant);
		}).onEmpty(() -> {
			//throw exception highly unlikely.
		});
		
		return findByCode(code);
	}

}
