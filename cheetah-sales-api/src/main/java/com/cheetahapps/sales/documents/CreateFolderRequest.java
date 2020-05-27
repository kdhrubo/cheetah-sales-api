package com.cheetahapps.sales.documents;

import lombok.Data;

@Data
class CreateFolderRequest {
	private String parentId;
	private String folder;
	private String documentSourceId;
	private String documentSource;
}
