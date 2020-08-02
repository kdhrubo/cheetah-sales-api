package com.cheetahapps.sales.vendor;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cheetahapps.sales.core.SearchRepositoryCustom;

interface VendorRepository extends MongoRepository<Vendor, String> , SearchRepositoryCustom<Vendor>{
	
	
}
