package com.cheetahapps.sales.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document("Task")
@TypeAlias("Task")
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
