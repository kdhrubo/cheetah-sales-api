package com.cheetahapps.sales.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	
	private String street;

	
	private String zip;

	
	private String poBox;

	
	private String city;

	
	private String state;

	
	private String country;
}