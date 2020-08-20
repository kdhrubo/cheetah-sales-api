package com.cheetahapps.sales.charge;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cheetahapps.sales.core.ExtensibleBase;
import com.cheetahapps.sales.tax.Tax;
import com.cheetahapps.sales.tax.TaxCalculation;
import com.cheetahapps.sales.tax.TaxType;
import com.cheetahapps.sales.tax.Tax.TaxBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Rajiv
 *
 */

@Document("Charge")
@Data
@TypeAlias("Charge")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Charge extends ExtensibleBase{
	
	private String chargeName;
	
	private ChargeFormat chargeFormat;
	
	private ChargeType chargeType;
	
	private Double chargeValue;
	
	private Boolean isTaxable;
	
	// Type of tax sale tax or service tax 
	private Tax tax;     // sale tax or service tax 

}
