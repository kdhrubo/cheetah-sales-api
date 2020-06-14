package com.cheetahapps.sales.setting;


import java.util.List;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import com.cheetahapps.sales.core.Base;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document("Setting")
@TypeAlias("Setting")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Setting extends Base {

	@Indexed(unique=true)
	private String name;

	private String provider;
	
	private String product;
	
	private boolean visible;
	
	private boolean system;
	
	private List<SettingItem> items;
	

}
