package com.cheetahapps.sales.documents;

import lombok.Data;

@Data
class CreateDocumentItemRequest {
	private String title;
	private String parentId;
	private String folder;
	private String documentSourceId;
	private String documentSource;
	private String documentType;
	private String documentTypeId;
}
