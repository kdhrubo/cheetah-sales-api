package com.cheetahapps.sales.task;

import java.time.LocalDate;
import java.time.LocalTime;

import com.cheetahapps.sales.core.Base;
import com.cheetahapps.sales.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Task extends Base{
	
	private String title;
	
	private LocalDate dueDate;
	private LocalTime dueTime;
	
	private User assigedTo; 
	
	private boolean done;
	
	private String relatedEntity;
	private String relatedEntityId;
	
}
