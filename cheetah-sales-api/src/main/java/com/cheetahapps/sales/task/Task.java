package com.cheetahapps.sales.task;

import java.time.LocalDateTime;

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
	
	private String subject;
	private String description;
	private LocalDateTime start;
	private LocalDateTime end;
	
	private User assigedTo; //Owner of the task
	
	private String relatedTo;
	private String relatedToId;

	private TaskStatus status;
	
}