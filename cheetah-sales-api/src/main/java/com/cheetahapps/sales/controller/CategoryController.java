package com.cheetahapps.sales.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import com.cheetahapps.sales.business.CategoryBusinessDelegate;
import com.cheetahapps.sales.domain.Category;
import com.cheetahapps.sales.domain.Contact;




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
	 * @PATH: /category/saveCat
	 * @Description: Saves new Category
	 * @param cat
	 * @return
	 */
	@PostMapping("/saveCat")
	public String saveCategory(@RequestBody Category cat) {
		log.info("Save Category API called");
		return cbd.saveCategory(cat);
		
	}
	
	
	/**
	 * @API: List Category
	 * @PATH: /category/listCat
	 * @Description: List all Categories
	 * @param cat
	 * @return
	 */
	@GetMapping("/listCat")
	public List<Category> listCategories() {
		
		return cbd.listCategories();
	}
	
	
	/**
	 * @API: Search Category
	 * @PATH: /category/searchCat
	 * @Description: List Category
	 * @param cat
	 * @return
	 */
	@GetMapping("/searchCat")
	public Category searchCategory(@RequestParam("rsql") String rsql) {
		log.info("inside searchCat and cat value is: "+ rsql);
		return cbd.searchCategory(rsql);
	}

}
