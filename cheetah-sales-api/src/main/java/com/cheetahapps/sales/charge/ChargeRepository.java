package com.cheetahapps.sales.charge;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cheetahapps.sales.core.SearchRepositoryCustom;

/**
 * 
 * @author Rajiv
 *
 */

@Repository
public interface ChargeRepository extends MongoRepository<Charge, String>, SearchRepositoryCustom<Charge>{
	
	
}
