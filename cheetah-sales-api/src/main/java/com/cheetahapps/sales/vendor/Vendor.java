package com.cheetahapps.sales.vendor;

import java.util.List;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cheetahapps.sales.core.ExtensibleBase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document("Vendor")
@Data
@TypeAlias("Vendor")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Vendor extends ExtensibleBase {

	private String name;
	private String phone;
	private String mobile;
	private String email;
	private String fax;
	private String website;
	
	private String glAccountId;
	private String glAccount;
	
	private String category;
	
	private String vendorNo;
	
	private String description;

	private Address address;
	
	private AssignedUser assignedTo;
	
	private List<Product> products;
	private List<Contact> contacts;

}