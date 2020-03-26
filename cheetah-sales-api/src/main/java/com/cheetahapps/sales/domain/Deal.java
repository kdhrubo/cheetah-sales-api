package com.cheetahapps.sales.domain;

import java.time.LocalDate;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document("Deal")
@Data
@TypeAlias("Deal")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Deal extends ExtensibleBase {

	private String name;

	private double amount;

	private Contact contact;

	private Account account;

	// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expectedClose;

	private PickList salesStage;

	private User assignedUser;

	private PickList leadSource;

	private String nextStep;

	private PickList opportunityType;

	private int probablity;

	private double forecastAmount;

	private String email;

	private PickList lostReason;

	// Keep a reference to the lead from which it was (if it was) converted
	private Lead lead;

	Team assignedTeam;

	private String description;

}