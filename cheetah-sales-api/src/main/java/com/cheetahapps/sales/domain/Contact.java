package com.cheetahapps.sales.domain;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Document("Contact")
@Data
@TypeAlias("Contact")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Contact extends ExtensibleBase{
	
	@DBRef
	private PickList salutation;

	private String firstName;
	

	private String lastName;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	
	

	private String email;
	
	private String mobile;
	
	private String officePhone;
	
	
	
	private String otherEmail;
	
	private String otherPhone;
	
	private String fax;
	
	



	private Account account;
	
	
	
	@DBRef
	private PickList title;
	

	private String department;
	

	private Contact reportsTo;

	private PickList leadSource;

	
	
	
	private User assignedUser;

	private Team assignedTeam;
	
	
	private PickList contactType;

	private PickList contactStatus;
	
	

	private boolean donotCall;

	private boolean emailOptIn;
	
	private boolean smsOptIn;

	private boolean notifyOwner;

	

	private String twitter;

	private String facebook;
	
	private String linkedIn;
	

	private Address primaryAddress;

	private Address secondaryAddress;

	private String description;
	
	private List<Note> notes;
	
	private List<Activity> activities;
	
	
}