package com.cheetahapps.sales.contact;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.cheetahapps.sales.core.ExtensibleBase;

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
public class Contact extends ExtensibleBase {

	private String salutationId;
	private String salutation;

	private String firstName;
	private String lastName;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;

	private String accountId;
	private String accountName;
	
	private String designationId;
	private String designation;

	private String department;

	private String reportsTo;
	private String reportsToId;

	private String leadSourceId;
	private String leadSource;

	private String assignedUser;
	private String assignedUserId;
	//private Team assignedTeam;

	private String contactTypeId;

	private String contactStatusId;

	private boolean donotCall;
	private boolean emailOptIn;
	private boolean smsOptIn;
	private boolean notifyOwner;

	private String description;

	private Address primaryAddress;
	private Address secondaryAddress;

	private List<Note> notes;

	private String email;
	private String otherEmail;

	private String phone;
	private String otherPhone;

	private String mobile;
	private String fax;

	// social

	//private String website; 
	private String twitter;
	private String facebook;
	private String linkedin;

}