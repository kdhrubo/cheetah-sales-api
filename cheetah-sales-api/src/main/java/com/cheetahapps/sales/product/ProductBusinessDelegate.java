package com.cheetahapps.sales.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.event.AddProductToLeadEvent;

/**
 * 
 * @author jay
 * @Description: Performs business logic for creation of product
 */
@Component
@Slf4j
public class ProductBusinessDelegate extends AbstractBusinessDelegate<Product, String> {

	private ProductRepository productRepository;

	public ProductBusinessDelegate(ProductRepository repository) {
		super(repository);
		this.productRepository = repository;
	}

	@Transactional(readOnly = true)
	public Page<Product> search(String rsql, Pageable pageable) {
		log.debug("Searching category - {}", rsql);

		return productRepository.search(rsql, pageable, Product.class);
	}
	
	
	
	@Override
	protected void beforeDelete(Product t) {
		t.setActive(false);
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
