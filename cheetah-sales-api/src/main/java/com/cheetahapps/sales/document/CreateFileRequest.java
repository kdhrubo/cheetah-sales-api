package com.cheetahapps.sales.document;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
class CreateFileRequest {
	
	private String container;
	private MultipartFile file;
	
	private String root;
}
