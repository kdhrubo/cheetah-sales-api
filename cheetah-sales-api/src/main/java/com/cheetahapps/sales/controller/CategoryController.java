package com.cheetahapps.sales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class CategoryController {
	
	@Autowired
	CategoryBusinessDelegate cbd;
	
	/**
	 * @API: Product Category
	 * @PATH: /category/savecat
	 * @Description: Saves new Category
	 * @param cat
	 * @return
	 */
	@PostMapping("/saveCat")
	public String saveCategory(@RequestBody Category cat) {
		log.info("Save Category API called");
		return cbd.saveCategory(cat);
		
	}

}
