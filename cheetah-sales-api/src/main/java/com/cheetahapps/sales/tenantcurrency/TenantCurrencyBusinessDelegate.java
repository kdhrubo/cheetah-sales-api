package com.cheetahapps.sales.tenantcurrency;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.problem.NoDataFoundProblem;
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
	
	@Transactional(readOnly = true)
	public Page<TenantCurrency> search(String rsql, Pageable pageable) {
		log.debug("Searching category - {}", rsql);
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, TenantCurrency.class);
		Criteria criteria = condition.query(new MongoVisitor());

		return tenantCurrencyRepository.search(criteria, pageable, TenantCurrency.class);
	}
	
	@Transactional
	public ConversionRate addConversionRate(String id, @Valid ConversionRate rate) {
		Optional<TenantCurrency> tc = findById(id);
		
		if(tc.isPresent()) {
			TenantCurrency tenantCurrency = tc.get();
			List<ConversionRate> rates = tenantCurrency.getConversionRates();
			
			if(rates == null) { //first time
				rates = new ArrayList<>();
				
			}
			else { //existing
				//find the active rate first
				
				Optional<ConversionRate> filteredRate = 
						rates.stream().filter(i -> i.getTo() == null).findFirst();
				
				if(filteredRate.isPresent()) {
					ConversionRate activeRate = filteredRate.get();
					activeRate.setTo(LocalDateTime.now()); //set end 
				}
				
			}
			
			rate.setFrom(LocalDateTime.now());
			rates.add(rate);
			tenantCurrency.setConversionRates(rates);
			save(tenantCurrency);
		}
		else {
			throw new NoDataFoundProblem("Tenant currency not found.");
		}
		
		return rate;
	}

}
