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

	private String productName;

	private float cost;

	private int prodPartNo;

	private int prodSerialNo;

	private String manufacturerName;

	private int manufacturerPartNo;

	private boolean availability;

	private boolean isActive;

	// @DBRef
	private String categoryId;

	private String currency;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date doa; // date of availability

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateCostPrice;

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

	private String stockUnits;

	private String reorderLevel;

	private int qtyInDemand;

	private String handler;

	private String supportContact;

	private String supportDesc;

	private String supportName;

	// in terms of no of months
	private String supportTerm;

	private String tags;

	private String prodImagePath;

	private int commissionRate;

	private boolean qtySchedulingEnabled;

	private boolean revenueSchedulingEnabled;

	private String taxClass;

	private String productType;

	private float prodHeight;

	private String prodHeightUnit;

	private float prodLength;

	private String prodLengthUnit;

	private float prodWidth;

	private String prodWidthUnit;

	private float prodWeight;

	private String prodWeightUnit;
	
	private List<Lead> leads;

}
