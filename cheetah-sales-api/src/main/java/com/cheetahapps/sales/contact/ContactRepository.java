package com.cheetahapps.sales.contact;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.cheetahapps.sales.core.SearchRepositoryCustom;

interface ContactRepository extends MongoRepository<Contact, String>, SearchRepositoryCustom<Contact> {
	
}
