package com.cheetahapps.sales.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cheetahapps.sales.domain.User;

import io.vavr.control.Option;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	
    Option<User> findByEmail(String email);

}
