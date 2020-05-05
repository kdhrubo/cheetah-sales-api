package com.cheetahapps.sales.pricebookentry;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.cheetahapps.sales.core.SearchRepositoryCustom;


public interface PriceBookEntryRepository extends MongoRepository<PriceBookEntry, String>, SearchRepositoryCustom<PriceBookEntry> {

}
