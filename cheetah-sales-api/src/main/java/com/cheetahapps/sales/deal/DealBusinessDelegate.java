package com.cheetahapps.sales.deal;

import java.util.Arrays;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.event.ConvertLeadEvent;
import com.cheetahapps.sales.problem.DeleteProblem;

import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DealBusinessDelegate extends AbstractBusinessDelegate<Deal, String> {
	private final DealRepository dealRepository;

	public DealBusinessDelegate(DealRepository repository) {
		super(repository);
		this.dealRepository = repository;
	}
	
	public Page<Deal> search(String rsql, Pageable pageable) {
		return dealRepository.search(rsql, pageable, Deal.class);
	}

	@Override
	protected void beforeCopy(Deal toBecopied) {
		log.debug("Copy deal.");
		toBecopied.setName("Copied " + toBecopied.getName());
	}
	
	
	
	@Override
	protected void beforeDelete(Deal t) {
		//move to rules engine
		
		if(t.isConvertedFromLead() || (t.getQuotes() != null && !t.getQuotes().isEmpty())){
			throw new DeleteProblem("Deal cannot be deleted when converted from a lead.");
		}
		
		if((t.getQuotes() != null && !t.getQuotes().isEmpty())){
			throw new DeleteProblem("Deal cannot be deleted. Quotes are associated with it.");
		}
	}

	@EventListener
	public void createFromLead(ConvertLeadEvent event) {
		//field mapping to come from db 
		
		Iterable<String> fields = Arrays.asList(
				
			  "description", 
				"leadSourceId", "leadSource"
				);

		
		if(event.isCreateContact()) {
			Deal deal = Deal.builder().build();
			
			BeanWrapper srcWrap = PropertyAccessorFactory.forBeanPropertyAccess(event.getLead());
			BeanWrapper trgWrap = PropertyAccessorFactory.forBeanPropertyAccess(deal);
			
			fields.forEach(f -> trgWrap.setPropertyValue(f, srcWrap.getPropertyValue(f)));
			log.info("Converted deal - {}", deal);
			
			save(deal);
		}
		
		
	}
}