package com.cheetahapps.sales.domain;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document("PickList")
@Data
@TypeAlias("PickList")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PickList extends Base{
	
	

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
