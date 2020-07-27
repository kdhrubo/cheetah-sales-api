package com.cheetahapps.sales.product;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
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

	private String name;
	private String partNo;
	private boolean active;
	private Category category;
	private Vendor vendor;
	private Manufacturer manufacturer;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date salesStartDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date salesEndDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date supportStartDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date supportEndDate;
	
	private String serialNo;
	private String manufacturerPartNo;
	private String vendorPartNo;
	
	private String url;
	private String glAccount; //can be hidden for now.
	private String productSheet;
	
	private String sku;
	
	private Currency currency;
	private double unitPrice; //standard price
	private double commissionRate; //percentage <=100 >0
	
	private String taxClass;
	private double purchaseCost;
	
	

	private String description;

	
	//LOV - Box, Carton, dozen, each, hours, impressions, Lb, M
	//pack, pages, pieces, quantity, reams, sheet, spiral binder, sq ft
	private String usageUnits;

	private int quantityPerUnit;

	private int qtyInStock;

	private int reorderLevel;

	private int qtyInDemand;

	private String handler;
	

	private String [] tags;

	private List<DocumentItem> medias;

	private List<Lead> leads;

}
