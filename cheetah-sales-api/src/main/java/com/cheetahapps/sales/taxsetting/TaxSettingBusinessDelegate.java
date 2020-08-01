package com.cheetahapps.sales.taxsetting;


import java.util.Optional;

import org.springframework.stereotype.Component;
import com.cheetahapps.sales.core.AbstractBusinessDelegate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
class TaxSettingBusinessDelegate extends AbstractBusinessDelegate<TaxSetting, String> {

	private final TaxSettingRepository taxSettingRepository;

	public TaxSettingBusinessDelegate(TaxSettingRepository taxSettingRepository) {
		super(taxSettingRepository);
		this.taxSettingRepository = taxSettingRepository;
	}
	
	public Optional<TaxSetting> getTaxSetting() {
		return this.taxSettingRepository.findAll().stream().findFirst();
	}
	
	

}