package com.cheetahapps.sales.tenantcurrency;

import java.util.List;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import com.cheetahapps.sales.core.Base;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @author jay
 * @Description: POJO for TenantCurrency table in Mongo database
 */

@Document("TenantCurrency")
@TypeAlias("TenantCurrency")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TenantCurrency extends Base {
	
	private String code;
	
	private String symbol;
	
	private String name;
	
	private boolean active;
	
	private boolean corporate;
	
	private List<ConversionRate> conversionRates;
}
