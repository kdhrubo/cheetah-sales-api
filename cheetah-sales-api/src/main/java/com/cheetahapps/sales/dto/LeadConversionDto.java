package com.cheetahapps.sales.dto;

import lombok.Data;

@Data
public class LeadConversionDto {

	private Long id;

	private boolean createOpportunity = true;

	private boolean createContact = true;

	private boolean createAccount = true;

}
