package com.cheetahapps.sales.vendor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
class Contact {

	private String id;
	
	private String salutationId;
	private String salutation;

	private String firstName;
	private String lastName;


}