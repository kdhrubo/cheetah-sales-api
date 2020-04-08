package com.cheetahapps.sales.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.extern.slf4j.Slf4j;
import com.cheetahapps.sales.domain.Category;
import com.cheetahapps.sales.repository.CategoryRepository;

/**
 * 
 * @author jay
 * Descriptions: Performs business logic for creation of product category
 */
@Component
@Slf4j
public class CategoryBusinessDelegate extends AbstractBaseBusinessDelegate<Category, String> {

	@Autowired
	CategoryRepository cr;
	
	public CategoryBusinessDelegate(CategoryRepository repository) {
		super(repository);
		this.repository = repository;
	}
	
	/**
	 * Perform request validation and save new category
	 * @param cat
	 * @return
	 */
	public String saveCategory(@RequestBody Category cat) {
		log.info("Category request accepted");
		
		if(cat.getProdCatName().isEmpty() || cat.getProdCatName() == null || cat.getProdCatName().equals(" "))
		{
			return "Product Category can not be empty or null";
		}
		else if(cat.getOrder() == 0)
		{
			return "Category order can not be 0 or blank";
		}
		else if (cr.findByOrderQuery(cat.getOrder()) != null)
		{
			return "Order can not be duplicate";
		}
		else
		{
		cr.save(cat);
		return "success cat";
		}
	}

}
