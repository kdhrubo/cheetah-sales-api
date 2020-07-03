package com.cheetahapps.sales.productprice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.github.rutledgepaulv.qbuilders.builders.GeneralQueryBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.visitors.MongoVisitor;
import com.github.rutledgepaulv.rqe.pipes.QueryConversionPipeline;

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
		QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, ProductPrice.class);
		Criteria criteria = condition.query(new MongoVisitor());

		return productPriceRepository.search(criteria, pageable, ProductPrice.class);
	}

}
