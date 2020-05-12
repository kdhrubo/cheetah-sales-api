package com.cheetahapps.sales.contact;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Link  {

	private String value;

	private String type; //Link type, auto detect or default = url

}
