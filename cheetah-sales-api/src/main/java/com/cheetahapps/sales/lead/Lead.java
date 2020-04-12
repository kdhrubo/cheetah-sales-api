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
	private String department;
	private String company;
	private int noOfEmployees;
	
	private double annualRevenue;
	private String website;
	private String fax;
	
	private String email;
	private String phone;
	private String mobile;
	
	private String otherEmail;
	private String otherPhone;

	@DBRef
	private PickList industry;
	@DBRef
	private PickList leadSource;
	@DBRef
	private PickList leadStatus;
	

	private String primaryStreet;
	private String primaryZip;
	private String primaryCity;
	private String primaryState;
	private String primaryCountry;

	
	private String secondaryStreet;
	private String secondaryZip;
	private String secondaryCity;
	private String secondaryState;
	private String secondaryCountry;
	
	
	@DBRef
	private User assignedTo; //TODO Change remove association

	private Long assignedTeam;

	private String description;

	private boolean donotCall;

	private boolean emailOptIn;
	
	private boolean smsOptIn;

	private boolean notifyOwner;
	
	private String twitter;
	private String facebook;
	private String linkedin;
	

	private boolean converted;

}
