package com.cheetahapps.sales.product;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Lead {
	
	private String id;
	private String firstName;
	private String lastName;
	
	private LocalDateTime createdDate;
	private LocalDateTime lastModifiedDate;
	
	
}
