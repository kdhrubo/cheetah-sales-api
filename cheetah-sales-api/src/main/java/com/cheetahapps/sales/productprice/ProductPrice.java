package com.cheetahapps.sales.productprice;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cheetahapps.sales.core.Base;
import com.cheetahapps.sales.core.ExtensibleBase;
import com.cheetahapps.sales.lead.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Rajiv
 *
 */

@Document("ProductPrice")
@Data
@TypeAlias("ProductPrice")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductPrice extends Base {

	private Boolean active;

	private Currency currency;
	
	private Double listPrice;

//	private List<Price> priceList;      //TODO : Align it 

	private PriceBook priceBook;

	private Product Product;

	private Double standardPrice;

	private Boolean useStandardPrice;

}
