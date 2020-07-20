package com.cheetahapps.sales.deal;

import java.time.LocalDate;
import java.util.List;

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

	private String salesStageId; 
	private String salesStage;
	
	private String assignedUserId;
	private String assignedUser;
	
	private String leadSourceId; 
	private String leadSource;

	private String nextStep;

	private String dealTypeId; 

	private int probablity; 

	private double forecastAmount;


	private String lostReasonId; 
	
	private String lostNotes; 
	
	private String teamId;
	
	private String leadId; 
	
	private boolean convertedFromLead;
	
	private String campaignId; //association 1:1

	private String description;
	
	//TBD
	// private String pipelineId; 
	// private double weightedRevenue;
	
	
	private List<Quote> quotes;
	

}