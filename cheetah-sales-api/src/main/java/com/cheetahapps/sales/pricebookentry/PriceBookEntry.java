package com.cheetahapps.sales.pricebookentry;

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
 * @Description: POJO for Price Book Entry table in Mongo database
 */

@Document("PriceBookEntry")
@TypeAlias("PriceBookEntry")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PriceBookEntry extends Base {
	
	private boolean active;
	
	private String currency;
	
	private String listPrice;
	
	private String priceBookId;
	
	private String productId;
	
	private float standardPrice;
	
	private boolean useStandardPrice;
}
