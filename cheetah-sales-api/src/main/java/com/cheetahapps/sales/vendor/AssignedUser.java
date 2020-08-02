package com.cheetahapps.sales.vendor;

import lombok.Value;

@Value(staticConstructor = "of")
public class AssignedUser {
	private String id, firstName, lastName, email;
}
