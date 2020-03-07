package com.cheetahapps.sales.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cheetahapps.sales.domain.PickList;


public interface PickListRepository extends MongoRepository<PickList, String> {
	List<PickList> findByDomain(String domain);

	List<PickList> findByDomainIn(Collection<String> domains);

	PickList findByDomainAndValue(String domain, String value);
}
