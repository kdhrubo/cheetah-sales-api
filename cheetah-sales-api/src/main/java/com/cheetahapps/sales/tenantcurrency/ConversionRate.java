package com.cheetahapps.sales.tenantcurrency;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConversionRate {
	
	private float rate;
	private LocalDateTime from;
	private LocalDateTime to;
	
	
	//AUDIT FIELDS
}
