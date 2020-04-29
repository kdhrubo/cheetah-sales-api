package com.cheetahapps.sales.link;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cheetahapps.sales.core.SearchRepositoryCustom;

interface LinkRepository extends MongoRepository<Link, String>, SearchRepositoryCustom<Link> {

}
