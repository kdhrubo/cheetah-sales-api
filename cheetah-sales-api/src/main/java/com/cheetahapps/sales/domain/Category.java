package com.cheetahapps.sales.domain;



import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @author jay
 * @Description: POJO for Category table in Mongo database
 */

@Document("Category")
@Data
@TypeAlias("Category")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Category extends ExtensibleBase{

	
	@Field("CategoryName")
	private String prodCatName;
	
	@Field("ParentCategory")
	private String parentCatName;
	
	@Field("CategoryDescription")
	private String prodCatDescription;
	
	
	@Field("CategoryOrder")
	private long order;
	
	

}
