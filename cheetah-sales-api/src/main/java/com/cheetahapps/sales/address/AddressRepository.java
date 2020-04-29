package com.cheetahapps.sales.address;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cheetahapps.sales.core.SearchRepositoryCustom;

interface AddressRepository extends MongoRepository<Address, String>, SearchRepositoryCustom<Address> {

}
