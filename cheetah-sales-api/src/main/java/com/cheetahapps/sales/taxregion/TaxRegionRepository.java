package com.cheetahapps.sales.taxregion;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cheetahapps.sales.core.SearchRepositoryCustom;

interface TaxRegionRepository extends MongoRepository<TaxRegion, String> , SearchRepositoryCustom<TaxRegion>{
	
	
}
