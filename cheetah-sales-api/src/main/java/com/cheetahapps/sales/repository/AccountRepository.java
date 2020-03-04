package com.cheetahapps.sales.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cheetahapps.sales.domain.Account;


@Repository
public interface AccountRepository extends MongoRepository<Account, String>, SearchRepositoryCustom<Account> {

}
