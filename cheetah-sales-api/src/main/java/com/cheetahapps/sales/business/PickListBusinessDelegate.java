package com.cheetahapps.sales.business;

import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cheetahapps.sales.domain.PickList;
import com.cheetahapps.sales.repository.PickListRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PickListBusinessDelegate extends AbstractBaseBusinessDelegate<PickList, String>{

	
	private PickListRepository repository;
	
	public PickListBusinessDelegate(PickListRepository repository) {
		super(repository);
	}

	@Transactional(readOnly = true)
	public List<PickList> findByDomain(String domain) {

		log.info("Getting domain values - " + domain);

		return repository.findByDomain(domain);
	}

	@Transactional(readOnly = true)
	public List<PickList> findByDomain(String domain, boolean required) {

		return repository.findByDomain(domain);
	}

}