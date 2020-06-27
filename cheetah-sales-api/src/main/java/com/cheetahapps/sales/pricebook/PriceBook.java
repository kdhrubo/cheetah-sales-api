package com.cheetahapps.sales.pricebook;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import com.cheetahapps.sales.core.Base;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @author jay
 * @Description: POJO for Price Book table in Mongo database
 */

@Document("PriceBook")
@TypeAlias("PriceBook")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PriceBook extends Base {
	
	
	
	private String priceBookName;
	
	private boolean active;
	
	private boolean isStandard;
	
	private String externalDataSrc;
	
	private String externalId;
	
	private String description;
}
