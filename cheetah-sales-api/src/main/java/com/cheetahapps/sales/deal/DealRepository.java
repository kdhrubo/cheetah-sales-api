package com.cheetahapps.sales.deal;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cheetahapps.sales.core.SearchRepositoryCustom;



@Repository
public interface DealRepository extends MongoRepository<Deal, String>, SearchRepositoryCustom<Deal> {

}
