package com.cheetahapps.sales.productprice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class Product {

	private String id;

	private String name;

	private float cost;

	private String currencyId;
	private String currency;
}
