package com.cheetahapps.sales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheetahapps.sales.business.TerritoryBusinessDelegate;
import com.cheetahapps.sales.domain.Territory;

@RestController
@RequestMapping("/territory")

public class TerritoryController extends AbstractBaseController<Territory, String> {

	@Autowired
	public TerritoryController(TerritoryBusinessDelegate service) {
		super(service);
	}

}