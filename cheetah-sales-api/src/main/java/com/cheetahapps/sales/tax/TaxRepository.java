package com.cheetahapps.sales.tax;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cheetahapps.sales.core.SearchRepositoryCustom;

interface TaxRepository extends MongoRepository<Tax, String> , SearchRepositoryCustom<Tax>{
	
	
}
