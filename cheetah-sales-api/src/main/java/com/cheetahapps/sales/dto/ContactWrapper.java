package com.cheetahapps.sales.dto;

import java.util.List;

import com.cheetahapps.sales.domain.Contact;

import lombok.Data;

@Data
public class ContactWrapper {
	
	private List<Contact> contacts;

	
}
