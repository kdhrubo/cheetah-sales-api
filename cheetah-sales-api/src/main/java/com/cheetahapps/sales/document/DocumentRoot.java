package com.cheetahapps.sales.document;


import lombok.Value;

@Value(staticConstructor = "of")
public class DocumentRoot {
	
	String name;
}
