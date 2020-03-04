package com.cheetahapps.sales.repository;


import org.springframework.stereotype.Repository;

import com.cheetahapps.sales.domain.Opportunity;



@Repository
public interface OpportunityRepository extends BaseMongoRepository<Opportunity, String> {

}
