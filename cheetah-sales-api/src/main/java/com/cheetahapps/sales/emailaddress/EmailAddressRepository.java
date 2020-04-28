package com.cheetahapps.sales.emailaddress;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cheetahapps.sales.core.SearchRepositoryCustom;

interface EmailAddressRepository extends MongoRepository<EmailAddress, String>, SearchRepositoryCustom<EmailAddress> {

}
