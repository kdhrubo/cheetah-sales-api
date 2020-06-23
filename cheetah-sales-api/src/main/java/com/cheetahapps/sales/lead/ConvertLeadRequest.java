package com.cheetahapps.sales.lead;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ConvertLeadRequest {
	@NotNull
	private String id;
	
	
	private boolean createAccount;
	private boolean createContact;
	private boolean createDeal = true;
}
