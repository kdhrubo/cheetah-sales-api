package com.cheetahapps.sales.event;

import com.cheetahapps.sales.lead.Lead;

import lombok.Value;

@Value(staticConstructor = "of")
public class LeadConvertedEvent {
	
	Lead lead;
	boolean createDeal, createAccount, createContact;

	
}
