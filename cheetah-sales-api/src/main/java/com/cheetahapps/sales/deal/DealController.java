package com.cheetahapps.sales.deal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cheetahapps.sales.core.AbstractBaseController;

@RestController
@RequestMapping("/deals")

public class DealController extends AbstractBaseController<Deal, String> {

	@Autowired
	public DealController(DealBusinessDelegate service) {
		super(service);
	}

}