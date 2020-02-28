package com.cheetahapps.sales.domain;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Document("Contact")
@Data
@TypeAlias("contact")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
	
	@Id
	private String id;

	private Salutation salutation;

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

	private LeadSource leadSource;

	private User assignedUser;

	private Team assignedTeam;

	private boolean donotCall;

	private boolean emailOptOut;

	private boolean notifyOwner;

	private ContactType contactType;

	private ContactStatus contactStatus;

	private String twitter;

	private String facebook;

	private Address primaryAddress;

	private Address secondaryAddress;

	private String description;
	
	private boolean deleted;

	private int version;

	@CreatedBy
	private User createdBy;

	@CreatedDate
	private LocalDateTime createdDate;

	@LastModifiedDate
	private LocalDateTime lastModifiedDate;

	@LastModifiedBy
	private User lastModifiedBy;
}