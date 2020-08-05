package com.cheetahapps.sales.territory;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TerritoryBusinessDelegate extends AbstractBusinessDelegate<Territory, String> {

	private TerritoryRepository territoryRepository;

	
	public TerritoryBusinessDelegate(TerritoryRepository repository) {
		super(repository);
		this.repository = repository;
	}


	public Page<Territory> search(String rsql, Pageable pageable) {
		log.info("Inside territory search");
	
		return territoryRepository.search(rsql, pageable, Territory.class);
	}
	
	public List<Territory> listAllTerritory() {
		log.info("Fectching territory from database.");
		return territoryRepository.findAll();
	}

}