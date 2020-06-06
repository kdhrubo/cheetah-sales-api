package com.cheetahapps.sales.contact;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.cheetahapps.sales.core.ExtensibleBase;
import com.cheetahapps.sales.domain.Team;
import com.cheetahapps.sales.user.User;

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

	private String designation;

	private String department;

	private Contact reportsTo;

	private String leadSourceId;

	private User assignedUser;

	private Team assignedTeam;

	private String contactTypeId;

	private String contactStatusId;

	private boolean donotCall;

	private boolean emailOptIn;

	private boolean smsOptIn;

	private boolean notifyOwner;

	private String description;

	private List<Address> addresses;

	private List<Note> notes;

	private String email;
	private String otherEmail;

	private String phone;
	private String otherPhone;

	private String mobile;
	private String fax;

	// social

	private String website;

	private String twitter;

	private String facebook;

	private String linkedIn;

}