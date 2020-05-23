package com.cheetahapps.sales.lead;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.event.LeadConvertedEvent;
import com.github.rutledgepaulv.qbuilders.builders.GeneralQueryBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.visitors.MongoVisitor;
import com.github.rutledgepaulv.rqe.pipes.QueryConversionPipeline;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
class LeadBusinessDelegate extends AbstractBusinessDelegate<Lead, String> {

	private final LeadRepository repository;

	private QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();

	public LeadBusinessDelegate(LeadRepository repository) {
		super(repository);
		this.repository = repository;
	}

	// TODO Handle lead conversion to account, opportunity, contact using event

	public Page<Lead> search(String rsql, Pageable pageable) {
		// "firstName==Paul;age==30"
		// "deleted==false"

		log.info("Inside search");

		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, Lead.class);
		Criteria criteria = condition.query(new MongoVisitor());

		return repository.search(criteria, pageable, Lead.class);
	}

	public Lead convert(String id, boolean createDeal, boolean createAccount, boolean createContact) {
		Optional<Lead> lead = this.repository.findById(id);

		if (lead.isPresent()) {
			// update status
			Lead l = lead.get();
			l.setConverted(true);
			repository.save(l);
			this.publishEvent(LeadConvertedEvent.of(l, createDeal, createAccount, createContact));

			return l;
		} else {
			throw new RuntimeException("");
		}

	}

}