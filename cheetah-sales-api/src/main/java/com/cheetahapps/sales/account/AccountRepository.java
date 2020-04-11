package com.cheetahapps.sales.account;


import org.springframework.data.mongodb.repository.MongoRepository;
import com.cheetahapps.sales.repository.SearchRepositoryCustom;

interface AccountRepository extends MongoRepository<Account, String>, SearchRepositoryCustom<Account> {

}
