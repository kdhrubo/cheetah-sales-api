package com.cheetahapps.sales.category;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cheetahapps.sales.core.SearchRepositoryCustom;

public interface CategoryRepository extends MongoRepository<Category, String>, SearchRepositoryCustom<Category> {

}
