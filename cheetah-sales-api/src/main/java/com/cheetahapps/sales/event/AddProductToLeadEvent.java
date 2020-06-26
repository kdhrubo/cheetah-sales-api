package com.cheetahapps.sales.event;

import lombok.Value;

@Value(staticConstructor = "of")
public class AddProductToLeadEvent {
	
	String leadId, firstName, lastName, productId;
	
}
