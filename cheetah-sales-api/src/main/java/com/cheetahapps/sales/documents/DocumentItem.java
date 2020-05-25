package com.cheetahapps.sales.documents;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cheetahapps.sales.core.ExtensibleBase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document("DocumentItem")
@Data
@TypeAlias("DocumentItem")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
class DocumentItem extends ExtensibleBase{
	
	private String title;
	
	private String name;
	
	private String externalId;
	
	private String externalParentId;
	
	private String externalParentName;
	
	private String documentSourceId;
	
	private String documentSource;
	
	private String documentType;
	
	private String documentTypeId;
	
	
	 
}
