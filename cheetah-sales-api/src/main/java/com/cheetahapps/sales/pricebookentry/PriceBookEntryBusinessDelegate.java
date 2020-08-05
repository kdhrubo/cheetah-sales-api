package com.cheetahapps.sales.pricebookentry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import com.cheetahapps.sales.core.AbstractBusinessDelegate;

/**
 * 
 * @author jay
 * @Description: Performs business logic for creation of price book Entry
 */
@Component
@Slf4j
public class PriceBookEntryBusinessDelegate extends AbstractBusinessDelegate<PriceBookEntry, String> {

	private PriceBookEntryRepository priceBookEntryRepository;

	public PriceBookEntryBusinessDelegate(PriceBookEntryRepository repository) {
		super(repository);
		this.priceBookEntryRepository = repository;
	}

	public Page<PriceBookEntry> search(String rsql, Pageable pageable) {
		log.debug("Searching category - {}", rsql);
        
		return priceBookEntryRepository.search(rsql, pageable, PriceBookEntry.class);
	}
	
       
	}
	
	
	


