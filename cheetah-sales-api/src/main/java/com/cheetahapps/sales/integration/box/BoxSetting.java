package com.cheetahapps.sales.integration.box;


import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cheetahapps.sales.core.Base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Document("BoxSetting")
@Data
@TypeAlias("BoxSetting")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BoxSetting extends Base{
	
	private String accessToken;
	

}
