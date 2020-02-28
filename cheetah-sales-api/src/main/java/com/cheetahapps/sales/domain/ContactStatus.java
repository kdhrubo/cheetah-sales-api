package com.cheetahapps.sales.domain;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class ContactStatus {
	
	@Id
	private String id;
	
	private String name;
	
	private String localName;
}
