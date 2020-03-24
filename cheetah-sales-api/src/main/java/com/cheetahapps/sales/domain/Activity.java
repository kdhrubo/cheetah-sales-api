package com.cheetahapps.sales.domain;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Activity  {

	private String subject;

	private String assignedTo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startTime;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endTime;

	private PickList activityType;


	private String location;
	private PickList status;

	private PickList priority;

	private boolean sendNotification;

	private String description;

	// @ElementCollection
	// List<Reminder> reminders;
}
