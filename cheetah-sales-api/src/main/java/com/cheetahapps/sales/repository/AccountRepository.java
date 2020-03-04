package com.cheetahapps.sales.repository;


import org.springframework.stereotype.Repository;

import com.cheetahapps.sales.domain.Account;


@Repository
public interface AccountRepository extends BaseMongoRepository<Account, String> {

}
