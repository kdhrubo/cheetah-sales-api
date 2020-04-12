package com.cheetahapps.sales.lead;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cheetahapps.sales.core.ExtensibleBase;
import com.cheetahapps.sales.picklist.PickList;
import com.cheetahapps.sales.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document("Lead")
@Data
@TypeAlias("Lead")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Lead extends ExtensibleBase {
	@DBRef
	private PickList salutation;
	private String firstName;
	private String lastName;
	
	private String designation;
	private String company;
	private int noOfEmployees;
	
	private double annualRevenue;
	private String website;
	private String fax;
	
	private String email;
	private String phone;
	private String mobile;

	@DBRef
	private PickList industry;
	@DBRef
	private PickList leadSource;
	@DBRef
	private PickList leadStatus;
	

	private String street;
	private String zip;
	private String city;
	private String state;
	private String country;

	@DBRef
	private User assignedTo;

	private Long assignedTeam;

	private String description;

	private boolean emailOptIn;
	private boolean smsOptIn;
	private String twitter;

	

	private boolean converted;

}
