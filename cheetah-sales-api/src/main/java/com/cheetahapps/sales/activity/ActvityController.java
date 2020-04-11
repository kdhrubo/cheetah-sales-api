package com.cheetahapps.sales.activity;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cheetahapps.sales.controller.AbstractBaseController;

@RestController
@RequestMapping("/activities")
public class ActvityController extends AbstractBaseController<Activity, String> {

	private final ActivityBusinessDelegate activityBusinessDelegate;

	public ActvityController(ActivityBusinessDelegate activityBusinessDelegate) {
		super(activityBusinessDelegate);
		this.activityBusinessDelegate = activityBusinessDelegate;
	}

	@GetMapping("/q")
	public List<Activity> searchAll(@RequestParam("rsql") String rsql) {
		return activityBusinessDelegate.searchAll(rsql);
	}
}