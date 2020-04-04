package com.cheetahapps.sales.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheetahapps.sales.business.TerritoryBusinessDelegate;
import com.cheetahapps.sales.domain.Territory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/territory")

public class TerritoryController extends AbstractBaseController<Territory, String> {
	
	private TerritoryBusinessDelegate territoryBusinessDelegate;

	@Autowired
	public TerritoryController(TerritoryBusinessDelegate service) {
		super(service);
		this.territoryBusinessDelegate = service;
	}
	
	@GetMapping("/listall")
	public List<Territory> findAllTerritory() {
		log.info("Fetching details for all territory");
		return territoryBusinessDelegate.listAllTerritory();
	}

}