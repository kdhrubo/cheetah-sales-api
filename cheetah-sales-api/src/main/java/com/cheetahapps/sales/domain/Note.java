package com.cheetahapps.sales.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Note {

	private String description;

	private User createdBy;
	
	private LocalDateTime createdDate; 

}
