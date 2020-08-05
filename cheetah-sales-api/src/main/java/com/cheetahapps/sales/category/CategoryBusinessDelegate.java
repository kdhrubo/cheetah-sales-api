package com.cheetahapps.sales.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;

/**
 * 
 * @author jay
 * @Description: Performs business logic for creation of product category
 */

@Component
@Slf4j
public class CategoryBusinessDelegate extends AbstractBusinessDelegate<Category, String> {

	private CategoryRepository categoryRepository;


	public CategoryBusinessDelegate(CategoryRepository repository) {
		super(repository);
		this.categoryRepository = repository;
	}

	public Page<Category> search(String rsql, Pageable pageable) {
		log.debug("Searching for category");
		return categoryRepository.search(rsql, pageable, Category.class);
	}

}
