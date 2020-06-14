package com.cheetahapps.sales.event;


import lombok.Value;

@Value(staticConstructor = "of")
public class SendEmailEvent<T> {
	
	String settingName;
	
	String from, name, to, subject, templateName; 
	
	T t;
	
	

}
