package com.cheetahapps.sales.taxsetting;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cheetahapps.sales.core.SearchRepositoryCustom;

interface TaxSettingRepository extends MongoRepository<TaxSetting, String> , SearchRepositoryCustom<TaxSetting>{
	
	
}
