package com.cheetahapps.sales.domain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;

@Document("Account")
@Data
@TypeAlias("account")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

	@Id
	private String id;

	private String name;

	private String website;

	private String phone;

	private String secondaryPhone;

	private String fax;

	private double annualRevenue;

	private int noOfEmployees;

	private String company;

	private String email;

	private String secondaryEmail;

	private String mobile;

	private String designation;

	private boolean emailOptOut;

	private String assignedUser;

	private Industry industry;

	private LeadType leadType;
	
	private Rating rating;
	
	private Team assignedTeam;

	private Address primaryAddress;
	private Address secondaryAddress;

	private String description;

	private User linkedUser;

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
	
	@Builder.Default
	private Map<String, String> extra = new HashMap<String, String>();

	// setter
	@JsonAnySetter
	public void set(String name, String value) {
		extra.put(name, value);
	}

	// getter
	@JsonAnyGetter
	public Object get(String name) {
		return extra.get(name);
	}

}