package com.cheetahapps.sales.tenant;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Value;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Value
public class TenantView {


	String id,name,code;
	
	Address address;
	
	boolean deleted, provisioned;
	
	LocalDateTime lastModifiedDate, createdDate;
	
	BoxSettings boxSettings;
}
