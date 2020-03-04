package com.cheetahapps.sales.domain;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Document("Contact")
@Data
@TypeAlias("contact")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Contact extends ExtensibleBase{
	
	

	private PickList salutation;

	private String firstName;

	private String lastName;

	private String email;

	private String phone;

	private String mobile;

	private String homePhone;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;

	private String fax;

	private Account account;

	private String title;

	private String department;

	private Contact parent;

	private PickList leadSource;

	private User assignedUser;

	private Team assignedTeam;

	private boolean donotCall;

	private boolean emailOptOut;

	private boolean notifyOwner;

	private PickList contactType;

	private PickList contactStatus;

	private String twitter;

	private String facebook;

	private Address primaryAddress;

	private Address secondaryAddress;

	private String description;
	
	
}