package com.cheetahapps.sales.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("PickList")
@Data
@TypeAlias("pickList")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PickList {
	
	@Id
	private String id;
	
	private boolean deleted;

	private int version;

	@CreatedBy
	private User createdBy;

	@CreatedDate
	private LocalDateTime createdDate;

	@LastModifiedDate
	private LocalDateTime lastModifiedDate;

	@LastModifiedBy
	private User lastModifiedBy;

	private String domain;
	private String value; 
	
	
	
	
	//contactstatus
	//contacttype
	//industry
	//leadsource
	//leadtype
	//lostreason
	//opportunitytype
	//rating
	//salesstage
	//salution
	
	
}
