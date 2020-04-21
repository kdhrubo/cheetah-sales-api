package com.cheetahapps.sales.product;

import java.util.Date;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import com.cheetahapps.sales.contact.Contact;
import com.cheetahapps.sales.core.Base;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @author jay
 * @Description: POJO for Product table in Mongo database
 */

@Document("Product")
@TypeAlias("Product")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Product extends Base {
	
	private boolean availability;
	
	//@DBRef
	private String categoryId;
	

	private float cost;
	
	private String currency;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date doa;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateCreated;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date recordModified;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateCostPrice;
	
	private String defaultPricingFormula;
	
	private String description;
	
	private String manufacturerName;
	
	private int manufacturerPartNo;
	
	private String productName;
	
	private String productUrl;
	
	private int stockUnits;
	
	private String contact;
	
	//in Terms of no of months
	private String supportTerm;
	
	private String tags;
	
	private String taxClass;
	
	private String productType;
	
	private int unitPrice;
	
	private int vendorPartNo;
	
	private float weight;

	private int order;

}
