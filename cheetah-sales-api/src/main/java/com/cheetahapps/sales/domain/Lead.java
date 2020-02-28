package com.cheetahapps.sales.domain;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;

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

@Document("Lead")
@Data
@TypeAlias("lead")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Lead {

	@Id
	private String id;

	private String firstName;

	private String lastName;

	private String company;

	private String email;

	private String phone;

	private String mobile;

	private String designation;

	private String website;

	private String fax;

	private double annualRevenue;

	private int noOfEmployees;

	private Long industry;

	private Long salutation;

	private Long leadSource;

	private Long leadStatus;

	private Long rating;

	private Address address;

	private User assignedTo;

	private Long assignedTeam;

	private String description;

	private boolean emailOptOut;

	private String twitter;

	private String facebook;

	private boolean converted;

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
