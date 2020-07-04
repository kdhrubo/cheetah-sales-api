package com.cheetahapps.sales.document;

import java.io.InputStream;


import lombok.Value;

@Value(staticConstructor = "of")
public class CreateFileEvent {
	
	String container,name, root;
	long size;
	InputStream input;
	
	
}
