package com.cheetahapps.sales.domain;

import java.time.LocalDate;
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
import org.springframework.format.annotation.DateTimeFormat;

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
public class Opportunity {

	@Id
	private String id;

	private String name;

	private double amount;

	private Contact contact;

	private Account account;

	// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expectedClose;

	private SalesStage salesStage;

	private User assignedUser;

	private LeadSource leadSource;

	private String nextStep;

	private OpportunityType opportunityType;

	private int probablity;

	private double forecastAmount;

	private String email;

	private LostReason lostReason;

	// Keep a reference to the lead from which it was (if it was) converted
	private Lead lead;

	Team assignedTeam;

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