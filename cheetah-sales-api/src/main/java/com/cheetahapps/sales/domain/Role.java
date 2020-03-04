package com.cheetahapps.sales.domain;


import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document
@EqualsAndHashCode(callSuper = true)
public class Role extends Base{
	
	public static final String USER = "ROLE_USER";
	
	
	private String name;
    
	
}