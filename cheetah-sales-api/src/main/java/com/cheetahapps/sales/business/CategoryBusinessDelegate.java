package com.cheetahapps.sales.business;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import com.cheetahapps.sales.domain.Category;
import com.cheetahapps.sales.repository.CategoryRepository;
import com.github.rutledgepaulv.qbuilders.builders.GeneralQueryBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.visitors.MongoVisitor;
import com.github.rutledgepaulv.rqe.pipes.QueryConversionPipeline;

/**
 * 
 * @author jay
 * @Description: Performs business logic for creation of product category
 */
@Component
@Slf4j
public class CategoryBusinessDelegate extends AbstractBaseBusinessDelegate<Category, String> {

	private CategoryRepository categoryRepository;

	private QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();

	public CategoryBusinessDelegate(CategoryRepository repository) {
		super(repository);
		this.categoryRepository = repository;
	}

	public Page<Category> search(String rsql, Pageable pageable) {
		// "firstName==Paul;age==30"
		// "deleted==false"
		log.debug("Searching category - {}", rsql);
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, Category.class);
		Criteria criteria = condition.query(new MongoVisitor());

		return categoryRepository.search(criteria, pageable, Category.class);
	}

}
