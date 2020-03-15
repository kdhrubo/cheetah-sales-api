package com.cheetahapps.sales.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import com.cheetahapps.sales.domain.Deal;



@Repository
public interface DealRepository extends MongoRepository<Deal, String>, SearchRepositoryCustom<Deal> {

}
