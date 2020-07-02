package com.cheetahapps.sales.tenantcurrency;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConversionRate {
	
	private float rate;
	private LocalDateTime from;
	private LocalDateTime to;
	
	@CreatedDate
	private LocalDateTime createdDate;
	@LastModifiedDate
	private LocalDateTime lastModifiedDate;
	@CreatedBy
	private String createdBy;
	@LastModifiedBy
	private String lastModifiedBy;
	
	
}
