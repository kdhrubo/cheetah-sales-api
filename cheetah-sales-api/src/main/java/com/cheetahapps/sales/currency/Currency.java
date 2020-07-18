package com.cheetahapps.sales.currency;


import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cheetahapps.sales.core.Base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document("Currency")
@TypeAlias("Currency")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Currency extends Base {
	
	private String code;
	private String symbol;
	private String name;
	

}
