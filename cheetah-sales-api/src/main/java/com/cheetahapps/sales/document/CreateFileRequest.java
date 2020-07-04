package com.cheetahapps.sales.document;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
class CreateFileRequest {
	private String name;
	private String parentName;
	private String parentId;
	private String documentSourceId;
	private String documentSource;
	
	private MultipartFile file;
	
}
