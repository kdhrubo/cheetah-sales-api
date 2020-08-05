package com.cheetahapps.sales.pricebook;

import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.event.ProvisionTenantEvent;

/**
 * 
 * @author jay
 * @Description: Performs business logic for creation of price book
 */
@Component
@Slf4j
public class PriceBookBusinessDelegate extends AbstractBusinessDelegate<PriceBook, String> {

	private PriceBookRepository priceBookRepository;

	public PriceBookBusinessDelegate(PriceBookRepository repository) {
		super(repository);
		this.priceBookRepository = repository;
	}
	
	@EventListener
	public void provision(ProvisionTenantEvent event) {
		if (!event.isExistingTenant()) {
			createStandardPriceBook();
		}
	}
	
	@Transactional
	public void createStandardPriceBook( ) {
		PriceBook book = PriceBook.builder().name("Standard").description("Standard Pricebook")
				.isStandard(true).build();
		this.save(book);
	}
	
	@Transactional(readOnly = true)
	public Page<PriceBook> search(String rsql, Pageable pageable) {
		log.debug("Searching category - {}", rsql);

		return priceBookRepository.search(rsql, pageable, PriceBook.class);
	}
	
	
	
}
