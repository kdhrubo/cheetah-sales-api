package com.cheetahapps.sales.documents;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.cheetahapps.sales.core.SearchRepositoryCustom;

interface DocumentItemRepository extends MongoRepository<DocumentItem, String>, SearchRepositoryCustom<DocumentItem> {

}
