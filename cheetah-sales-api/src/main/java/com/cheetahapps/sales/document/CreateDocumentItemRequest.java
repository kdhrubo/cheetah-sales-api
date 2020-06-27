package com.cheetahapps.sales.document;


import lombok.Data;

@Data
class CreateDocumentItemRequest {
	private String name;
	private String parentName;
	private String parentId;
	private String documentSourceId;
	private String documentSource;
	
	
}
