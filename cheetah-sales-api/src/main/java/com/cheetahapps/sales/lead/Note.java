package com.cheetahapps.sales.lead;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Note {

	private String description;
	@JsonUnwrapped
	private User createdBy;
	private LocalDateTime createdDate;
}
