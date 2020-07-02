package com.cheetahapps.sales.core;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"id"})
public class Base {
	
	@Id
	private String id;
	
	private boolean deleted;
	
	@Version
	private int version;

	@CreatedBy
	private String createdBy;
	
	@LastModifiedBy
	private String lastModifiedBy;

	@CreatedDate
	private LocalDateTime createdDate;

	@LastModifiedDate
	private LocalDateTime lastModifiedDate;

	private String source;
	
	private String externalSourceId;

}
