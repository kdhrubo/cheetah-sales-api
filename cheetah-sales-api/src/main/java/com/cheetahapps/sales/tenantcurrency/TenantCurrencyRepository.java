package com.cheetahapps.sales.tenantcurrency;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.cheetahapps.sales.core.SearchRepositoryCustom;


public interface TenantCurrencyRepository extends MongoRepository<TenantCurrency, String>, SearchRepositoryCustom<TenantCurrency> {

}
