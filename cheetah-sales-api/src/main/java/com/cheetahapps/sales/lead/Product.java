package com.cheetahapps.sales.lead;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
class Product {
	
	private String id;
	
	private String name;
	
	private float cost;
	
	private String currencyId;
	private String currency;
}
