package com.cheetahapps.sales.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Tenant {
	
	@Id
	private String id;
	
	
	private String name;
	
	private String code;
	
	private	Address address;
	
	private boolean deleted;
	
	@LastModifiedDate
	private LocalDateTime lastModifiedDate;
	
	@CreatedDate
	private LocalDateTime createdDate;

}
