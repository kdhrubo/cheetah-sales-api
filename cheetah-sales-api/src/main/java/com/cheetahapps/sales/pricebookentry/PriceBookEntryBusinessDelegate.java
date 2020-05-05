package com.cheetahapps.sales.pricebookentry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import com.cheetahapps.sales.core.AbstractBaseBusinessDelegate;
import com.github.rutledgepaulv.qbuilders.builders.GeneralQueryBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.visitors.MongoVisitor;
import com.github.rutledgepaulv.rqe.pipes.QueryConversionPipeline;

/**
 * 
 * @author jay
 * @Description: Performs business logic for creation of price book Entry
 */
@Component
@Slf4j
public class PriceBookEntryBusinessDelegate extends AbstractBaseBusinessDelegate<PriceBookEntry, String> {

	private PriceBookEntryRepository priceBookEntryRepository;

	private QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();

	public PriceBookEntryBusinessDelegate(PriceBookEntryRepository repository) {
		super(repository);
		this.priceBookEntryRepository = repository;
	}

	public Page<PriceBookEntry> search(String rsql, Pageable pageable) {
		log.debug("Searching category - {}", rsql);
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, PriceBookEntry.class);
		Criteria criteria = condition.query(new MongoVisitor());
        
		return priceBookEntryRepository.search(criteria, pageable, PriceBookEntry.class);
	}
	
       
	}
	
	
	


