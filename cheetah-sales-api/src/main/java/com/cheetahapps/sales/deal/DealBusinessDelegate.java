package com.cheetahapps.sales.deal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class DealBusinessDelegate extends AbstractBusinessDelegate<Deal, String> {
	private final DealRepository dealRepository;
	
	private QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();

	public DealBusinessDelegate(DealRepository repository) {
		super(repository);
		this.dealRepository = repository;
	}
	
	public Page<Deal> search(String rsql, Pageable pageable) {
		//"firstName==Paul;age==30"
		//"deleted==false"
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, Deal.class);
	    Criteria criteria = condition.query(new MongoVisitor());
		
		return dealRepository.search(criteria, pageable, Deal.class);
	}
}