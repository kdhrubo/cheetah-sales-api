package com.cheetahapps.sales.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cheetahapps.sales.domain.Lead;

@Repository
public interface LeadRepository extends MongoRepository<Lead, String> , SearchRepositoryCustom<Lead>{
	
	
}
