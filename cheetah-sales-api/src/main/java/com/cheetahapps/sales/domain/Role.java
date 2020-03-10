package com.cheetahapps.sales.domain;


import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document
public class Role {
	
	public static final String USER = "ROLE_USER";
	public static final String COMPANY_ADMIN = "ROLE_COMPANY_ADMIN";
	
	private String name;
    
	
}