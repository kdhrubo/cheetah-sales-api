package com.cheetahapps.sales.lead;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Address {
	
	private String street;

	
	private String zip;

	
	private String city;

	
	private String state;

	
	private String country;
	
}