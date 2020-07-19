package com.cheetahapps.sales.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cheetahapps.sales.core.SearchRepositoryCustom;

import io.vavr.control.Option;

@Repository
public interface UserRepository extends MongoRepository<User, String> ,  SearchRepositoryCustom<User>{
	
	
    Option<User> findByEmail(String email);
    Option<User> findByTenantId(String tenantId);
    
}
