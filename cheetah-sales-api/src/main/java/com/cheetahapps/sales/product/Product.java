package com.cheetahapps.sales.product;

import java.util.Date;

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
	
	private boolean availability;
	
	//@DBRef
	private String categoryId;
	

	private float cost;
	
	private String currency;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date doa;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateCostPrice;
	
	private String productName;
	
	private boolean isActive;
	
	private int prodPartNo;
	
	private String manufacturerName;
	
	private int manufacturerPartNo;
	
	private int prodSerialNo;
	
	private String vendorName;
	
	private int vendPartNo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date salesStartDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date salesEndDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date supportStartDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date supportEndDate;
	
	private String productUrl;
	
	private int generalLedgerAccount;
	
	private String productSheetpath;
	
	private String productSku;
	
	private String barCode;
	
	private String externalDisplayUrl;
	
	private String externalDataSource;
	
	private String externalId;
	
	private String prodCurrency;
	
	private String description;
	
	private String billingType;
	
	private String usageUnits;
	
	private int quantityPerUnit;
	
	private int stockUnits;
	
	private String reorderLevel;
	
	private int qtyInDemand;
	
	private String handler;
	
	private String supportContact;
	
	private String supportDesc;
	
	private String supportName;
	
	//in terms of no of months
	private String supportTerm;
	
	private String tags;
	
	private String prodImagePath;
	
	private int commissionRate;
	
	private boolean qtySchedulingEnabled;
	
	private boolean revenueSchedulingEnabled;
	
	private String taxClass;
	
	private String productType;
	
	private String prodHeight;
	
	private float prodHeightUnit;
	
	private String prodLength;
	
	private float prodLengthUnit;
	
	private String prodWidth;
	
	private float prodWidthUnit;
	
	private String prodWeight;
	
	private float prodWeightUnit;

}
