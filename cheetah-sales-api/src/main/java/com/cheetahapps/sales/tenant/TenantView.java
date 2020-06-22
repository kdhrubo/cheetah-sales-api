package com.cheetahapps.sales.tenant;

import java.time.LocalDateTime;

import com.cheetahapps.sales.integration.box.BoxSetting;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Value;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Value
public class TenantView {


	String id,name,code;
	
	boolean deleted, provisioned;
	
	LocalDateTime lastModifiedDate, createdDate;
	
	BoxSetting boxSettings;
}
