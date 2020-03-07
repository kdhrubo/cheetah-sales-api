package com.cheetahapps.sales.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import com.cheetahapps.sales.domain.Opportunity;



@Repository
public interface OpportunityRepository extends MongoRepository<Opportunity, String>, SearchRepositoryCustom<Opportunity> {

}
