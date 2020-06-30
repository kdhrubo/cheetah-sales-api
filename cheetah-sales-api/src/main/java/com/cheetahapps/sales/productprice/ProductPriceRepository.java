package com.cheetahapps.sales.productprice;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cheetahapps.sales.core.SearchRepositoryCustom;

@Repository
public interface ProductPriceRepository
		extends MongoRepository<ProductPrice, String>, SearchRepositoryCustom<ProductPrice> {

}
