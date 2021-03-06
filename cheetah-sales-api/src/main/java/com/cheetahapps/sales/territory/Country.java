package com.cheetahapps.sales.territory;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//TODO - Replace with PICKLIST
@Document("Country")
@Data
@TypeAlias("country")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Country {
	
	@Id
	private String id;
	
	private String state;
	
	private String city;
	
	private String postalCode;
	
	private Integer areaCode;

}
