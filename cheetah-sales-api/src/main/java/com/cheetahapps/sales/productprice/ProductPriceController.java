package com.cheetahapps.sales.productprice;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cheetahapps.sales.core.AbstractController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/product-price")
@Slf4j
public class ProductPriceController extends AbstractController<ProductPrice, String> {

	private ProductPriceBusinessDelegate productPriceBusinessDelegate;

	public ProductPriceController(ProductPriceBusinessDelegate productPriceBusinessDelegate) {
		super(productPriceBusinessDelegate);
		this.productPriceBusinessDelegate = productPriceBusinessDelegate;
	}

	@GetMapping("/search")
	public Page<ProductPrice> search(@RequestParam("rsql") String rsql, @PageableDefault Pageable pageable) {
		log.info("ProductPrice business delegate - {}", this.businessDelegate);
		log.info("rsql - {}", rsql);
		log.info("pageable - {}", pageable);
		return productPriceBusinessDelegate.search(rsql, pageable);
	}

	@PostMapping("/import")
	public List<ProductPrice> saveAll(@RequestBody ProductPriceList productPriceList) {
		log.info("Importing ProductPriceList initiated --> {}", productPriceList);
		return productPriceBusinessDelegate.saveAll(productPriceList.getListProductPrice());
	}

}
