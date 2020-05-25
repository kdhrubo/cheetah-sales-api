package com.cheetahapps.sales.integration.box;

import io.vavr.control.Option;
import lombok.Data;

@Data
public class BoxCreateFolderEvent {
	
	private String name;
	private String id;
	private Option<String> parent;
}
