package com.cheetahapps.sales.form;

import java.util.List;
import java.util.Map;

import lombok.Data;
@Data
public class FormFieldConfig {
	
	private String key;
	
	private String name;
	private String type;	
	private String component;	
	private String className;	
	
	private String template;
	private String defaultValue;
	private boolean hide;
	private boolean hideExpression;
	private boolean expressionProperties;
	private boolean	focus;
	
	
	private List<FormFieldConfig> fieldGroup;	
	private FormFieldConfig fieldArray;
	
	private String fieldGroupClassName;
	
	private Map templateOptions;
	
	
	private boolean section; //external use
	private String sectionLabel; //external use

}
