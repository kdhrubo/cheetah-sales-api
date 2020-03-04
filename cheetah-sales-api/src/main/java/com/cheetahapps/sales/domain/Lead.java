package com.cheetahapps.sales.domain;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document("Lead")
@Data
@TypeAlias("lead")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Lead extends ExtensibleBase {

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

}
