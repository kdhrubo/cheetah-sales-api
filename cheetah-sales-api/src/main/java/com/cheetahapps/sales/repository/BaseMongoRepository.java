package com.cheetahapps.sales.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface BaseMongoRepository<T, I> extends MongoRepository<T, I>{
	
	Page<T> findAllByDeleted(boolean deleted, Page<T> page);

}
