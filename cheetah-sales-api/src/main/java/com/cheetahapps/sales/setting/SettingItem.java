package com.cheetahapps.sales.setting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SettingItem {

	private String key;
	
	private String value;
	
	private boolean sensitive;
}
