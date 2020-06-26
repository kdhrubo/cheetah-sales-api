package com.cheetahapps.sales.product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.event.AddProductToLeadEvent;
import com.github.rutledgepaulv.qbuilders.builders.GeneralQueryBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.visitors.MongoVisitor;
import com.github.rutledgepaulv.rqe.pipes.QueryConversionPipeline;

/**
 * 
 * @author jay
 * @Description: Performs business logic for creation of product
 */
@Component
@Slf4j
public class ProductBusinessDelegate extends AbstractBusinessDelegate<Product, String> {

	private ProductRepository productRepository;

	private QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();

	public ProductBusinessDelegate(ProductRepository repository) {
		super(repository);
		this.productRepository = repository;
	}

	public Page<Product> search(String rsql, Pageable pageable) {
		log.debug("Searching category - {}", rsql);
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, Product.class);
		Criteria criteria = condition.query(new MongoVisitor());

		return productRepository.search(criteria, pageable, Product.class);
	}
	
	@EventListener
	public void handleLead(AddProductToLeadEvent event) {
		Optional<Product> product = this.productRepository.findById(event.getProductId());
		
		if(product.isPresent()) {
			
			List<Lead> leads = product.get().getLeads();
			
			if(leads == null) {
				leads = new ArrayList<>();
			}
			
			
		}
	}

}
