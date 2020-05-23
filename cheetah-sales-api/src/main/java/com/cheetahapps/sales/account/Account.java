package com.cheetahapps.sales.account;



import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cheetahapps.sales.core.ExtensibleBase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document("Account")
@Data
@TypeAlias("Account")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Account extends ExtensibleBase{


	private String name;

	private String website;

	private String phone;

	private String secondaryPhone;

	private String fax;

	private double annualRevenue;

	private int noOfEmployees;

	private String company;

	private String email;

	private String secondaryEmail;

	private String mobile;

	private String designation;

	private boolean emailOptOut;

	private String assignedUserId;
	
	private String assignedUserName;

	private String industryId;
	
	private String industryName;

	private String leadTypeId;
	
	private String leadTypeName;
	
	private String ratingId;
	
	private String ratingValue;
	
	private String assignedTeamId;

	private Address primaryAddress;
	
	private Address secondaryAddress;

	private String description;

	private String linkedUserId;
	
	private String linkedUserName;

	
}