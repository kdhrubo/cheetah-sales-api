package com.cheetahapps.sales.lead;

import lombok.Value;

@Value(staticConstructor = "of")
public class AssignedUser {
	private String id, firstName, lastName, email;
}
