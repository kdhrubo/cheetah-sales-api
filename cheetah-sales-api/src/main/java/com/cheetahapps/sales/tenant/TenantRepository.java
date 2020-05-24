package com.cheetahapps.sales.tenant;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.vavr.control.Option;

interface TenantRepository extends MongoRepository<Tenant, String> {
	
	Option<Tenant> findFirstByProvisioned(boolean provisioned);
	TenantView findByCode(String code);
}
