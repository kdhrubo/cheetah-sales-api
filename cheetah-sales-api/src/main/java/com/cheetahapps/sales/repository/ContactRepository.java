package com.cheetahapps.sales.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cheetahapps.sales.domain.Contact;


@Repository
public interface ContactRepository extends MongoRepository<Contact, String>, SearchRepositoryCustom<Contact> {
	
}
