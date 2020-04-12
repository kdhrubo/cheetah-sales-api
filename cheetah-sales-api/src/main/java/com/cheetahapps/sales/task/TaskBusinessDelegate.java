package com.cheetahapps.sales.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import com.cheetahapps.sales.core.AbstractBaseBusinessDelegate;
import com.github.rutledgepaulv.qbuilders.builders.GeneralQueryBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.visitors.MongoVisitor;
import com.github.rutledgepaulv.rqe.pipes.QueryConversionPipeline;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TaskBusinessDelegate extends AbstractBaseBusinessDelegate<Task, String> {
	
	private TaskRepository repository;
	
	private QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();
	
	public TaskBusinessDelegate(TaskRepository repository) {
		super(repository);
		this.repository = repository;
	}
	
	
	public Page<Task> search(String rsql, Pageable pageable) {
		//"firstName==Paul;age==30"
		//"deleted==false"
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, Task.class);
	    Criteria criteria = condition.query(new MongoVisitor());
		
		return repository.search(criteria, pageable, Task.class);
	}
	
}