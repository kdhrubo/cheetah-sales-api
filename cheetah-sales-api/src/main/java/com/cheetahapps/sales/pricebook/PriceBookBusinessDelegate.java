package com.cheetahapps.sales.pricebook;
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
 * @Description: Performs business logic for creation of price book
 */
@Component
@Slf4j
public class PriceBookBusinessDelegate extends AbstractBaseBusinessDelegate<PriceBook, String> {

	private PriceBookRepository priceBookRepository;

	private QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();

	public PriceBookBusinessDelegate(PriceBookRepository repository) {
		super(repository);
		this.priceBookRepository = repository;
	}

	public Page<PriceBook> search(String rsql, Pageable pageable) {
		log.debug("Searching category - {}", rsql);
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, PriceBook.class);
		Criteria criteria = condition.query(new MongoVisitor());
        
		return priceBookRepository.search(criteria, pageable, PriceBook.class);
	}
	
       
	}
	
	
	


