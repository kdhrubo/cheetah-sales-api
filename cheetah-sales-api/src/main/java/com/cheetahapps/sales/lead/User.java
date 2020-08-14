package com.cheetahapps.sales.lead;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private String userId;
	private String firstName; 
	private String lastName;
	private String email;
}
