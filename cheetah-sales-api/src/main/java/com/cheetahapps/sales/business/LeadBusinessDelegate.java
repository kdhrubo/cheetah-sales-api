package com.cheetahapps.sales.business;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import com.cheetahapps.sales.domain.Lead;
import com.cheetahapps.sales.repository.LeadRepository;
import com.github.rutledgepaulv.qbuilders.builders.GeneralQueryBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.visitors.MongoVisitor;
import com.github.rutledgepaulv.rqe.pipes.QueryConversionPipeline;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LeadBusinessDelegate extends AbstractBaseBusinessDelegate<Lead, String> {

	private LeadRepository repository;
	
	private QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();
	
	public LeadBusinessDelegate(LeadRepository repository) {
		super(repository);
		this.repository = repository;
	}

	// TODO Handle lead conversion to account, opportunity, contact using event
	
	public Page<Lead> search(String rsql, Pageable pageable) {
		//"firstName==Paul;age==30"
		//"deleted==false"
		
		log.info("Inside search");
		
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, Lead.class);
	    Criteria criteria = condition.query(new MongoVisitor());
		
		return repository.search(criteria, pageable, Lead.class);
	}
}