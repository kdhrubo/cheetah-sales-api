package com.cheetahapps.sales.deal;

import java.time.LocalDate;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.cheetahapps.sales.core.ExtensibleBase;
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

	//association 1:1
	private String contactId; 
	private String contactName; 

	private String accountId;
	private String accountName;
	//association 1:1

	// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expectedClose;

	private String salesStageId; //pl
	
	
	private String assignedUserId;
	private String assignedUser;
	
	private String leadSourceId; //pl

	private String nextStep;

	private String dealTypeId; //pl

	private int probablity; 

	private double forecastAmount;

	private String email;

	private String lostReasonId; //pl
	
	private String lostNotes; //description
	
	private String teamId; // association 1:1
	

	// Keep a reference to the lead from which it was (if it was) converted
	private String leadId; // association 1:1 , never allowed to modify not shown in create
	
	private boolean convertedFromLead;
	
	private String campaignId; //association 1:1

	private String description;
	
	//TBD
	// private String pipelineId; 
	// private double weightedRevenue;
	
	

}