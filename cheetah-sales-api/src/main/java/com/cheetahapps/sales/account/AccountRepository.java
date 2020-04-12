package com.cheetahapps.sales.account;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.cheetahapps.sales.core.SearchRepositoryCustom;

interface AccountRepository extends MongoRepository<Account, String>, SearchRepositoryCustom<Account> {

}
