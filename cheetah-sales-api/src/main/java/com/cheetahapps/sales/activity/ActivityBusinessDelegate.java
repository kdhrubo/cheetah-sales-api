package com.cheetahapps.sales.activity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import com.cheetahapps.sales.business.AbstractBaseBusinessDelegate;
import com.cheetahapps.sales.domain.Activity;
import com.github.rutledgepaulv.qbuilders.builders.GeneralQueryBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.visitors.MongoVisitor;
import com.github.rutledgepaulv.rqe.pipes.QueryConversionPipeline;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
class ActivityBusinessDelegate extends AbstractBaseBusinessDelegate<Activity, String> {
	
	private ActivityRepository repository;
	
	private QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();
	
	public ActivityBusinessDelegate(ActivityRepository repository) {
		super(repository);
		this.repository = repository;
	}
	
	
	public Page<Activity> search(String rsql, Pageable pageable) {
		//"firstName==Paul;age==30"
		//"deleted==false"
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, Activity.class);
	    Criteria criteria = condition.query(new MongoVisitor());
		
		return repository.search(criteria, pageable, Activity.class);
	}
	
}