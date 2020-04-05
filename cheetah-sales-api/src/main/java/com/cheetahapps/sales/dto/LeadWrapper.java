package com.cheetahapps.sales.dto;

import java.util.List;

import com.cheetahapps.sales.domain.Lead;

import lombok.Data;

@Data
public class LeadWrapper {
	
	private List<Lead> leads;


}
