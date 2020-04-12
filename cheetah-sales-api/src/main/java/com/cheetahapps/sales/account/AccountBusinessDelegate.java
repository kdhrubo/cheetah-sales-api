package com.cheetahapps.sales.account;

import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.cheetahapps.sales.core.AbstractBaseBusinessDelegate;
import com.cheetahapps.sales.event.LeadConvertedEvent;
import com.github.rutledgepaulv.qbuilders.builders.GeneralQueryBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.visitors.MongoVisitor;
import com.github.rutledgepaulv.rqe.pipes.QueryConversionPipeline;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
class AccountBusinessDelegate extends AbstractBaseBusinessDelegate<Account, String> {
	
	private AccountRepository repository;
	
	private QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();
	
	public AccountBusinessDelegate(AccountRepository repository) {
		super(repository);
		this.repository = repository;
	}
	
	
	public Page<Account> search(String rsql, Pageable pageable) {
		//"firstName==Paul;age==30"
		//"deleted==false"
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, Account.class);
	    Criteria criteria = condition.query(new MongoVisitor());
		
		return repository.search(criteria, pageable, Account.class);
	}
	
	@Async
	@EventListener
	public void handle(LeadConvertedEvent event) {
		
	}
	
}