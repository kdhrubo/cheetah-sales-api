package com.cheetahapps.sales.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateFolderEvent {
	
	private String name;
	private String container;
	
	private String root;
}
