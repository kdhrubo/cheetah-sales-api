package com.cheetahapps.sales.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import com.cheetahapps.sales.business.CategoryBusinessDelegate;
import com.cheetahapps.sales.domain.Category;

/**
 * 
 * @author jay
 * 
 */
@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController extends AbstractBaseController<Category, String>{

	
	private final CategoryBusinessDelegate categoryBusinessDelegate;
	
	public CategoryController(CategoryBusinessDelegate categoryBusinessDelegate) {
		super(categoryBusinessDelegate);
		this.categoryBusinessDelegate = categoryBusinessDelegate;
	}

	
	@GetMapping("/q")
	public Page<Category> search(@RequestParam("rsql") String rsql, Pageable pageable) {
		log.debug("Search category");
		return categoryBusinessDelegate.search(rsql, pageable);
	}

}
