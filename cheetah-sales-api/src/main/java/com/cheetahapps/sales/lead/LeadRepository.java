package com.cheetahapps.sales.lead;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cheetahapps.sales.core.SearchRepositoryCustom;

interface LeadRepository extends MongoRepository<Lead, String> , SearchRepositoryCustom<Lead>{
	
	
}
