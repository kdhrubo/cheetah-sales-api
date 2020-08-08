package com.cheetahapps.sales.pricebook;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import com.cheetahapps.sales.core.AbstractController;
import com.cheetahapps.sales.lead.Lead;

/**
 * 
 * @author jay
 * 
 */
@RestController
@RequestMapping("/pricebooks")
@Slf4j
public class PriceBookController extends AbstractController<PriceBook, String>{

	
	private final PriceBookBusinessDelegate priceBookBusinessDelegate;
	
	public PriceBookController(PriceBookBusinessDelegate priceBookBusinessDelegate) {
		super(priceBookBusinessDelegate);
		this.priceBookBusinessDelegate = priceBookBusinessDelegate;
	}

	
	@GetMapping("/search")
	public Page<PriceBook> search(@RequestParam("rsql") String rsql, Pageable pageable) {
		log.debug("Search price book");
		return priceBookBusinessDelegate.search(rsql, pageable);
	}
	
	@PostMapping("/import")
	public List<PriceBook> saveAll(@RequestBody PriceBookList priceBookWrapper) {
		log.info("## In saveAll --> {}", priceBookWrapper);
		
		return this.priceBookBusinessDelegate.saveAll(priceBookWrapper.getPriceBook());
	}
	
	@PostMapping("/{id}/products")
	public PriceBook addProduct(@PathVariable String id, @RequestBody @Valid Product product) {
		return this.priceBookBusinessDelegate.addProduct(id, product);
	}
	
	@PostMapping("/{id}/products/{productId}")
	public PriceBook removeProduct(@PathVariable String id, @PathVariable String productId) {
		return this.priceBookBusinessDelegate.removeProduct(id, productId);
	}
	

}
