package com.cheetahapps.sales.product;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cheetahapps.sales.core.SearchRepositoryCustom;

public interface ProductRepository extends MongoRepository<Product, String>, SearchRepositoryCustom<Product> {

}
