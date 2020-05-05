package com.cheetahapps.sales.pricebook;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.cheetahapps.sales.core.SearchRepositoryCustom;


public interface PriceBookRepository extends MongoRepository<PriceBook, String>, SearchRepositoryCustom<PriceBook> {

}
