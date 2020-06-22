package com.cheetahapps.sales.tenantcurrency;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.github.rutledgepaulv.qbuilders.builders.GeneralQueryBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.visitors.MongoVisitor;
import com.github.rutledgepaulv.rqe.pipes.QueryConversionPipeline;

/**
 * 
 * @author jay
 * @Description: Performs business logic for creation of TenantCurrency
 */
@Component
@Slf4j
public class TenantCurrencyBusinessDelegate extends AbstractBusinessDelegate<TenantCurrency, String> {

	private TenantCurrencyRepository tenantCurrencyRepository;

	private QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();

	public TenantCurrencyBusinessDelegate(TenantCurrencyRepository repository) {
		super(repository);
		this.tenantCurrencyRepository = repository;
	}

	public Page<TenantCurrency> search(String rsql, Pageable pageable) {
		log.debug("Searching category - {}", rsql);
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, TenantCurrency.class);
		Criteria criteria = condition.query(new MongoVisitor());
        
		return tenantCurrencyRepository.search(criteria, pageable, TenantCurrency.class);
	}
	
       
	}
	
	
	


