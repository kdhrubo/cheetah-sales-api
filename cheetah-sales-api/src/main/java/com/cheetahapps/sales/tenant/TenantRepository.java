package com.cheetahapps.sales.tenant;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.vavr.control.Option;

interface TenantRepository extends MongoRepository<Tenant, String> {
	
	TenantView findByCode(String code);
	
	Option<Tenant> findByName(String name);
	
}
