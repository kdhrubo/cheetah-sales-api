package com.cheetahapps.sales.repository;

import java.util.Collection;
import java.util.List;


import com.cheetahapps.sales.domain.PickList;


public interface PickListRepository extends BaseMongoRepository<PickList, String> {
	List<PickList> findByDomain(String domain);

	List<PickList> findByDomainIn(Collection<String> domains);

	PickList findByDomainAndValue(String domain, String value);
}
