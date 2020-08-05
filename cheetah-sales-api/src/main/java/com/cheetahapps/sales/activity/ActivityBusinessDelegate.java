package com.cheetahapps.sales.activity;

import java.util.List;

import org.springframework.stereotype.Component;
import com.cheetahapps.sales.core.AbstractBusinessDelegate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
class ActivityBusinessDelegate extends AbstractBusinessDelegate<Activity, String> {
	
	private final ActivityRepository activityRepository;
	
	public ActivityBusinessDelegate(ActivityRepository repository) {
		super(repository);
		this.activityRepository = repository;
	}
	
	
	public List<Activity> searchAll(String rsql) {	
		log.debug("Search all activities");
		return activityRepository.searchAll(rsql, Activity.class);
	}
	
}