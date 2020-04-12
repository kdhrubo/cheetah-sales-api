package com.cheetahapps.sales.contact;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cheetahapps.sales.core.SearchRepositoryCustom;


@Repository
public interface ContactRepository extends MongoRepository<Contact, String>, SearchRepositoryCustom<Contact> {
	
}
