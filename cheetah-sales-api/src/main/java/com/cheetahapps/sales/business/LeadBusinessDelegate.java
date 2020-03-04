package com.cheetahapps.sales.business;

import org.springframework.stereotype.Component;

import com.cheetahapps.sales.domain.Lead;
import com.cheetahapps.sales.repository.LeadRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LeadBusinessDelegate extends AbstractBaseBusinessDelegate<Lead, String> {

	private LeadRepository repository;

	public LeadBusinessDelegate(LeadRepository repository) {
		super(repository);
		this.repository = repository;
	}

	// TODO Handle lead conversion to account, opportunity, contact using event
}