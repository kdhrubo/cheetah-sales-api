package com.cheetahapps.sales.pricebook;

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
import com.cheetahapps.sales.event.ProvisionTenantEvent;
import com.cheetahapps.sales.problem.NoDataFoundProblem;

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
	
	@Transactional
	public PriceBook addProduct(String id, Product product) {
		Optional<PriceBook> priceBook = this.priceBookRepository.findById(id);
		
		if (priceBook.isPresent()) {
			PriceBook priceBookDB = priceBook.get();
			List<Product> products = priceBookDB.getProducts();
			
			if(products == null) {
				products = new ArrayList<>();
			}
			
			if(!products.contains(product)) {
				products.add(product);
				priceBookDB.setProducts(products);
				
				this.repository.save(priceBookDB);
			}
			
		} else {
			throw new NoDataFoundProblem("PriceBook with given id not found.");
		}

		return priceBook.get();
	}
	
	@Transactional
	public PriceBook removeProduct(String id, String productId) {
		Optional<PriceBook> priceBook = this.priceBookRepository.findById(id);
		Product p = Product.builder().id(productId).build();
		if (priceBook.isPresent()) {
			PriceBook priceBookDB = priceBook.get();
			List<Product> products = priceBookDB.getProducts();
			
			if(products != null && products.contains(p)) {
				boolean status = products.remove(p);
				log.info("Product removed from price book - {}", status);
				
				if(status) {
					this.repository.save(priceBookDB);
				}
			}
			
			
		} else {
			throw new NoDataFoundProblem("PriceBook with given id not found.");
		}

		return priceBook.get();
	}
	
	
}
