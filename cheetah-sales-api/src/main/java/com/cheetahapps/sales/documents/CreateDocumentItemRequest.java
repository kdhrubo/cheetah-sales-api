package com.cheetahapps.sales.documents;

import lombok.Data;

@Data
class CreateDocumentItemRequest {
	private String name;
	private String parentName;
	private String parentId;
	private String documentSourceId;
	private String documentSource;
	private String documentType;
	
}
