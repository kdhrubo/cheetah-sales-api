package com.cheetahapps.sales.activity;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cheetahapps.sales.core.AbstractController;

@RestController
@RequestMapping("/activities")
public class ActvityController extends AbstractController<Activity, String> {

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