package com.cheetahapps.sales.tax;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cheetahapps.sales.core.ExtensibleBase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document("Tax")
@Data
@TypeAlias("Tax")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Tax extends ExtensibleBase {
	
	private String name;
	private boolean active;
	
	private TaxCalculation calculation;
	private TaxType taxType;
	
	private double value;
	
	
	
}
