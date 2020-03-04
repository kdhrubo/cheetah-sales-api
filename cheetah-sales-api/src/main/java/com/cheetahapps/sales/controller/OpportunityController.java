package com.cheetahapps.sales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.cheetahapps.sales.business.OpportunityBusinessDelegate;
import com.cheetahapps.sales.domain.Opportunity;

@Controller
@RequestMapping("/opportunities")

public class OpportunityController extends AbstractBaseController<Opportunity, String> {

	@Autowired
	public OpportunityController(OpportunityBusinessDelegate service) {
		super(service);
	}

}