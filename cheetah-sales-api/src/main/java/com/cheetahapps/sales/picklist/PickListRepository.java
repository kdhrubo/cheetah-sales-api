package com.cheetahapps.sales.picklist;

import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.vavr.control.Option;


public interface PickListRepository extends MongoRepository<PickList, String> {
	List<PickList> findByDomain(String domain);

	List<PickList> findByDomainIn(Collection<String> domains);

	Option<PickList> findByDomainAndValue(String domain, String value);
}
