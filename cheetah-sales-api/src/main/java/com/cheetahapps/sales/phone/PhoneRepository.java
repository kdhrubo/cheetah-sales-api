package com.cheetahapps.sales.phone;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cheetahapps.sales.core.SearchRepositoryCustom;

interface PhoneRepository extends MongoRepository<Phone, String>, SearchRepositoryCustom<Phone> {

}
