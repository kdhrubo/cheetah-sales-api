package com.cheetahapps.sales.lead;

import java.util.List;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cheetahapps.sales.core.ExtensibleBase;

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
	private String salutationId;
	private String salutation;
	
	private String firstName;
	private String lastName;
	
	private String designationId;
	private String designation;
	
	
	private String department;
	private String company;
	private int noOfEmployees;
	
	private double annualRevenue;
	
	//currency associated with annual revenue required.
	
	private String email;
	private String otherEmail;
	
	private String phone;
	private String otherPhone;
	
	private String mobile;
	private String fax;
	
	
	private String industryId;
	private String industry;
	
	private String leadSourceId;
	private String leadSource;
	
	private String leadStatusId;
	private String leadStatus;

	private Address primaryAddress;
	private Address secondaryAddress;
	
	private AssignedUser assignedTo;
	

	private String description;

	private boolean donotCall;
	private boolean emailOptIn;
	private boolean smsOptIn;
	private boolean notifyOwner;
	
	private String website;
	private String twitter;
	private String facebook;
	private String linkedin;
	

	private boolean converted;
	
	private List<Product> products;

}
