package com.cheetahapps.sales.activity;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.cheetahapps.sales.controller.AbstractBaseController;
import com.cheetahapps.sales.domain.Activity;

@RestController
@RequestMapping("/activities")
public class ActvityController extends AbstractBaseController<Activity, String> {
	
	private final ActivityBusinessDelegate accountBusinessDelegate;

	@Autowired
	public ActvityController(ActivityBusinessDelegate accountBusinessDelegate) {
		super(accountBusinessDelegate);
		this.accountBusinessDelegate = accountBusinessDelegate;
	}
	
	@GetMapping("/q")
	public Page<Activity> search(@RequestParam("rsql") String rsql, Pageable pageable) {
		return accountBusinessDelegate.search(rsql, pageable);
	}
}