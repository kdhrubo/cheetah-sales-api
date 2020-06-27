package com.cheetahapps.sales.document;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.cheetahapps.sales.core.SearchRepositoryCustom;

interface DocumentItemRepository extends MongoRepository<DocumentItem, String>, SearchRepositoryCustom<DocumentItem> {

}
