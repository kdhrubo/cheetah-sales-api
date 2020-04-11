package com.cheetahapps.sales.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cheetahapps.sales.domain.Category;

public interface CategoryRepository extends MongoRepository<Category, String>, SearchRepositoryCustom<Category> {

}
