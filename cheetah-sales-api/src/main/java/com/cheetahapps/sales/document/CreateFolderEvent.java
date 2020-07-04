package com.cheetahapps.sales.document;

import lombok.Value;


@Value(staticConstructor = "of")
public class CreateFolderEvent {
	
	private String name;
	private String container;
	
	private String root;
}
