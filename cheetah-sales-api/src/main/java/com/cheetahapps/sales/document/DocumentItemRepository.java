package com.cheetahapps.sales.document;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.cheetahapps.sales.core.SearchRepositoryCustom;

import io.vavr.control.Option;

interface DocumentItemRepository extends MongoRepository<DocumentItem, String>, SearchRepositoryCustom<DocumentItem> {
	
	Option<DocumentItem> findByPath(String container);

}
