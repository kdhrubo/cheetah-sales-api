package com.cheetahapps.sales.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cheetahapps.sales.domain.Category;




@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
	
	@Query("{order : ?0}")
    public Category findByOrderQuery(long order);
	
	@Query("{prodCatName : ?0}")
	public Category findByName(String prodCatName);

}
