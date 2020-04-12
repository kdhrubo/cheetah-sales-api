package com.cheetahapps.sales.activity;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.cheetahapps.sales.core.Base;
import com.cheetahapps.sales.picklist.PickList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


@Document("Activity")
@Data
@TypeAlias("Activity")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Activity extends Base {

	private String subject;
	private String location;

	

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	
	private LocalTime startTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	
	private LocalTime endTime;

	private PickList activityType;
	private PickList status;
	private PickList priority;

	private String assignedTo;
	

	private String description;

	// List<Reminder> reminders;
	private boolean sendNotification;
	
	private String relatedEntity;
	private String relatedEntityId;
}
