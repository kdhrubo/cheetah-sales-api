package com.cheetahapps.sales.lead;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.event.ConvertLeadEvent;
import com.cheetahapps.sales.problem.NoDataFoundProblem;
import com.github.rutledgepaulv.qbuilders.builders.GeneralQueryBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.visitors.MongoVisitor;
import com.github.rutledgepaulv.rqe.pipes.QueryConversionPipeline;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
class LeadBusinessDelegate extends AbstractBusinessDelegate<Lead, String> {

	private final LeadRepository leadRepository;

	public LeadBusinessDelegate(LeadRepository repository) {
		super(repository);
		this.leadRepository = repository;
	}

	public Page<Lead> search(String rsql, Pageable pageable) {
		QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, Lead.class);
		Criteria criteria = condition.query(new MongoVisitor());

		return leadRepository.search(criteria, pageable, Lead.class);
	}
	
	

	@Override
	protected void beforeCopy(Lead toBecopied) {
		toBecopied.setFirstName("Copied " + toBecopied.getFirstName());
		
	}

	public Lead convert(String id, boolean createDeal, boolean createAccount, boolean createContact) {
		Optional<Lead> lead = this.repository.findById(id);
		

		if (lead.isPresent()) {
			// update status
			log.info("Starting conversion of lead.");
			Lead l = lead.get();
			l.setConverted(true);
			repository.save(l);
			this.publish(ConvertLeadEvent.of(l, createDeal, createAccount, createContact));

			return l;
		} else {
			throw new NoDataFoundProblem("Lead with given id not found.");
		}

	}

}