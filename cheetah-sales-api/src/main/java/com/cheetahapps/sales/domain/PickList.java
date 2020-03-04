package com.cheetahapps.sales.domain;



import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
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
@TypeAlias("picklist")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PickList {
	
	private Long id;

	private String domain;
	private String value;
	private String localName; 
	
	
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
