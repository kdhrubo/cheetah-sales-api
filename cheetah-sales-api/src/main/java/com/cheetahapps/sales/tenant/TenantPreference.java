package com.cheetahapps.sales.tenant;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document("TenantPreference")
@TypeAlias("TenantPreference")
public class TenantPreference {
	
	
	

	@Id
	private String tenantId;
	
	private String currencyId;
	private String currency;
	
	private String countryId;
	private String country;
	
	private String timezoneId;
	private String timeZone;
	
	private String languageId;
	private String languate;
	
	private String dateFormatId;
	private String dateFormat;
	
	
	private	Address address;
	
	
	@LastModifiedDate
	private LocalDateTime lastModifiedDate;
	
	@CreatedDate
	private LocalDateTime createdDate;
	
	

}
