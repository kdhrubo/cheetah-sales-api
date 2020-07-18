package com.cheetahapps.sales.currency;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.cheetahapps.sales.core.SearchRepositoryCustom;

import io.vavr.control.Option;


public interface CurrencyRepository extends MongoRepository<Currency, String>, 
	SearchRepositoryCustom<Currency> {
	
	Option<Currency> findByCode(String code);
}
