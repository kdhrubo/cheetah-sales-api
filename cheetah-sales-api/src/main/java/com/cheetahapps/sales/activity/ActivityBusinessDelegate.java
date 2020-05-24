package com.cheetahapps.sales.activity;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.github.rutledgepaulv.qbuilders.builders.GeneralQueryBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.visitors.MongoVisitor;
import com.github.rutledgepaulv.rqe.pipes.QueryConversionPipeline;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
class ActivityBusinessDelegate extends AbstractBusinessDelegate<Activity, String> {
	
	private final ActivityRepository activityRepository;
	
	private QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();
	
	public ActivityBusinessDelegate(ActivityRepository repository) {
		super(repository);
		this.activityRepository = repository;
	}
	
	
	public List<Activity> searchAll(String rsql) {
		//"firstName==Paul;age==30"
		//"deleted==false"
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, Activity.class);
	    Criteria criteria = condition.query(new MongoVisitor());
		
		return activityRepository.searchAll(criteria, Activity.class);
	}
	
}