package com.cheetahapps.sales.business;

import org.springframework.stereotype.Component;

import com.cheetahapps.sales.domain.Opportunity;
import com.cheetahapps.sales.repository.OpportunityRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OpportunityBusinessDelegate extends AbstractBaseBusinessDelegate<Opportunity, String> {

	public OpportunityBusinessDelegate(OpportunityRepository repository) {
		super(repository);
	}

}