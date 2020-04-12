package com.cheetahapps.sales.core;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
public class ExtensibleBase extends Base{
	
	
	private Map<String, String> extra = new HashMap<String, String>();

	// setter
	@JsonAnySetter
	public void set(String name, String value) {
		extra.put(name, value);
	}

	// getter
	@JsonAnyGetter
	public Map<String, String> get() {
		return extra;
	}

}
