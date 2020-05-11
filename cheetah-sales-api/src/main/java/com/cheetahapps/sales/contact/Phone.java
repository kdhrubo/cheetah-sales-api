package com.cheetahapps.sales.contact;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Phone {

	private String value;

	private boolean primary; 

}
