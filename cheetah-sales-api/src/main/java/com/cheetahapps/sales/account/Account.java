package com.cheetahapps.sales.account;

import java.time.LocalDateTime;

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
	
	private String email;
	private String website;
	private String phone;
	
	private int noOfEmployees;
	
	private String industryId;
	private String industry;

	private double annualRevenue;
	
	
	// Customer, Competitor, Partner, Analyst, Vendor, Lead, Sales Qualified Lead
	
	private String accountType; 
	private String accountTypeId; 
	
	private String ownerShip;
	
	private String tickerSymbol;
	
	private String assignedUserId;
	private String assignedUserName;
	
	private boolean notifyOwner;
	
	private String sicCode;
	
	private String orgNum;
	
	private boolean fromLead;
	
	private String twitter;

	
	private LocalDateTime lastContactedOn;
	private String lastConcatedVia;
	
	private String slaId;
	private String sla; 
	
	private String emailDomain;
	
	
	//Active, Inactive
	private String organizationStatusId;
	private String organizationStatus;
	
	private String territoryId; //sometimes called region
	private String territory;
	
	private int profileScore;
	private float profileRating;
	
	
	private String otherEmail;

	
	private String otherPhone;

	private String mobile;
	private String fax;

	

	private String leadTypeId;
	private String leadTypeName;

	

	

	private Address primaryAddress;
	private Address secondaryAddress;

	private String description;

	
	
	private String facebook;
	private String linkedin;
	
	private boolean donotCall;
	private boolean emailOptIn;
	private boolean smsOptIn;
	
	
	//RELATIONS
	//1. TASKS
	//2. MEETINGS
	//3. TODOS
	//4. NOTES
	//5. EMAILS
	//6. WORKORDERS
	//7. DOCUMENTS
	//8. PRODUCTS
	//9. SERVICE CONTRACTS
	//10. EMAILLIST & CAMPAIGNS
	//11. SMS
	
	
	
}