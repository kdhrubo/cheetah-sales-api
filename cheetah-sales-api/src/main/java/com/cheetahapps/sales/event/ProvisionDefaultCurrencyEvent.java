package com.cheetahapps.sales.event;

import lombok.Value;

@Value(staticConstructor = "of")
public class ProvisionDefaultCurrencyEvent {
	boolean existingTenant;
	String code, symbol, name;
}
