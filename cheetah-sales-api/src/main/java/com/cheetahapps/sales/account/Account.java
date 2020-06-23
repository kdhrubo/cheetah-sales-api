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
public class Account extends ExtensibleBase {

	private String name;

	private double annualRevenue;

	private int noOfEmployees;

	private String email;
	private String otherEmail;

	private String phone;
	private String otherPhone;

	private String mobile;
	private String fax;


	private String assignedUserId;
	private String assignedUserName;
	private String assignedTeamId;

	private String industryId;
	private String industry;

	private String leadTypeId;
	private String leadTypeName;

	private String ratingId;
	private String ratingValue;

	

	private Address primaryAddress;
	private Address secondaryAddress;

	private String description;

	private String website;
	private String twitter;
	private String facebook;
	private String linkedin;
	
	private boolean donotCall;
	private boolean emailOptIn;
	private boolean smsOptIn;
	

}