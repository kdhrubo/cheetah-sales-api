package com.cheetahapps.sales.contact;

import java.time.LocalDateTime;

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
	private String createdBy;
	private LocalDateTime createdDate;
}
