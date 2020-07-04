package com.cheetahapps.sales.document;


import lombok.Data;

@Data
class CreateFolderRequest {
	private String name;
	private String container;
	
	private String root;
}
