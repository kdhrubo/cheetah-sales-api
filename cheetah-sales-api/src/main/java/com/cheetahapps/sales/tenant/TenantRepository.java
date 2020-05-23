package com.cheetahapps.sales.tenant;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TenantRepository extends MongoRepository<Tenant, String> {
	
	List<Tenant> findByProvisionedFalse();
}
