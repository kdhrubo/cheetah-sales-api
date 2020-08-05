package com.cheetahapps.sales.productprice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Rajiv
 *
 */

@Slf4j
@Service
public class ProductPriceBusinessDelegate extends AbstractBusinessDelegate<ProductPrice, String> {

	private ProductPriceRepository productPriceRepository;

	public ProductPriceBusinessDelegate(ProductPriceRepository productPriceRepository) {
		super(productPriceRepository);
		this.productPriceRepository = productPriceRepository;
	}

	public Page<ProductPrice> search(String rsql, Pageable pageable) {
		log.debug("Search");
		return productPriceRepository.search(rsql, pageable, ProductPrice.class);
	}

}
