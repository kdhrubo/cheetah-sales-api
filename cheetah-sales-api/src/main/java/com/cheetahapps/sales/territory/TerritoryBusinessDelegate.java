package com.cheetahapps.sales.territory;

import java.util.List;


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
public class TerritoryBusinessDelegate extends AbstractBusinessDelegate<Territory, String> {

	private TerritoryRepository territoryRepository;

	private QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();

	
	public TerritoryBusinessDelegate(TerritoryRepository repository) {
		super(repository);
		this.repository = repository;
	}


	public Page<Territory> search(String rsql, Pageable pageable) {
		log.info("Inside territory search");
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, Territory.class);
		Criteria criteria = condition.query(new MongoVisitor());
		return territoryRepository.search(criteria, pageable, Territory.class);
	}
	
	public List<Territory> listAllTerritory() {
		log.info("Fectching territory from database.");
		return territoryRepository.findAll();
	}

}