package com.cheetahapps.sales.product;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import com.cheetahapps.sales.core.AbstractController;

/**
 * 
 * @author jay
 * 
 */
@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController extends AbstractController<Product, String>{

	
	private final ProductBusinessDelegate productBusinessDelegate;
	
	public ProductController(ProductBusinessDelegate productBusinessDelegate) {
		super(productBusinessDelegate);
		this.productBusinessDelegate = productBusinessDelegate;
	}

	
	@GetMapping("/search")
	public Page<Product> search(@RequestParam("rsql") String rsql, Pageable pageable) {
		log.debug("Search category");
		return productBusinessDelegate.search(rsql, pageable);
	}
	
	@PostMapping("/import")
	public List<Product> saveAll(@RequestBody ProductList productWrapper) {
		log.info("## In saveAll --> {}", productWrapper);
		
		return this.productBusinessDelegate.saveAll(productWrapper.getProduct());
	}

}
