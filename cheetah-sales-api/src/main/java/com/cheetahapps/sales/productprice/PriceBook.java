package com.cheetahapps.sales.productprice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Rajiv
 * @Description: POJO for Price Book table in Mongo database
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
class PriceBook  {
	
	private String id;

	private String priceBookName;

}
