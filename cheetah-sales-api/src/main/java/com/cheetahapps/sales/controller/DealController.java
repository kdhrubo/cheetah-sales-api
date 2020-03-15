package com.cheetahapps.sales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cheetahapps.sales.business.DealBusinessDelegate;
import com.cheetahapps.sales.domain.Deal;

@RestController
@RequestMapping("/deals")

public class DealController extends AbstractBaseController<Deal, String> {

	@Autowired
	public DealController(DealBusinessDelegate service) {
		super(service);
	}

}