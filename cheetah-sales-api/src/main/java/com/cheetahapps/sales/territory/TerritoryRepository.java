package com.cheetahapps.sales.territory;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cheetahapps.sales.core.SearchRepositoryCustom;

@Repository
public interface TerritoryRepository extends MongoRepository<Territory, String> , SearchRepositoryCustom<Territory>{
	
	
}
