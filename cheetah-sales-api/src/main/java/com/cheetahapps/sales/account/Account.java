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
	
	//row 1
	private String name;
	private String website;
	private int noOfEmployees;
	private double annualRevenue;
	
	//row 2
	private String email;
	private String otherEmail;
	private String phone;
	private String otherPhone;
	
	//row 3
	
	private String fax;
	private String twitter;
	private String facebook;
	private String linkedin;
	
	
	//row 4
	private String industryId;
	private String industry;
	
	// Customer, Competitor, Partner, Analyst, Vendor, Lead, Sales Qualified Lead
	
	private String accountType; 
	private String accountTypeId; 
	
	private String ownerShip;
	private String ticker;
	
	//row 5
	private String assignedUserId;
	private String assignedUserName;
	
	private String accountStatusId;
	private String accountStatus;
	
	private String territoryId; //sometimes called region
	private String territory;
	
	private String slaId;
	private String sla; 

	//row 6
	
	private boolean donotCall;
	private boolean emailOptIn;
	private boolean smsOptIn;
	private boolean notifyOwner;
	
	//row 7 // to be added to UI
	private LocalDateTime lastContactedOn;
	private String lastConcatedVia;
	private int profileScore;
	private float profileRating;
	
	//Active, Inactive
	
	//row 8
	private Address primaryAddress;
	
	//row 9
	private Address secondaryAddress;

	//row 10
	private String description;

	
	
	// row 4
	//private String sicCode;
	//private String orgNum;
	private boolean fromLead; // not shown in form
	private String leadId; // not shown in form
	
	
	
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