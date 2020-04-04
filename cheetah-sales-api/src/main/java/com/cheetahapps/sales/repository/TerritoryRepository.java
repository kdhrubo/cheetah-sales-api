package com.cheetahapps.sales.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cheetahapps.sales.domain.Lead;
import com.cheetahapps.sales.domain.Territory;

@Repository
public interface TerritoryRepository extends MongoRepository<Territory, String> , SearchRepositoryCustom<Territory>{
	
	
}
