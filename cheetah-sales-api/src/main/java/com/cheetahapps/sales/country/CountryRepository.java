package com.cheetahapps.sales.country;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.cheetahapps.sales.core.SearchRepositoryCustom;


import io.vavr.control.Option;


public interface CountryRepository extends MongoRepository<Country, String>, 
	SearchRepositoryCustom<Country> {
	
	Option<Country> findByName(String name);
}
