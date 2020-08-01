package com.cheetahapps.sales.taxsettings;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheetahapps.sales.core.AbstractController;


import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/taxes")
@Slf4j
public class TaxSettingController extends AbstractController<TaxSetting, String> {

	private TaxSettingBusinessDelegate taxSettingBusinessDelegate;

	public TaxSettingController(TaxSettingBusinessDelegate taxSettingBusinessDelegate) {
		super(taxSettingBusinessDelegate);
		this.taxSettingBusinessDelegate = taxSettingBusinessDelegate;
	}

	@GetMapping("/q")
	public TaxSetting findOne() {
		return taxSettingBusinessDelegate.getTaxSetting().orElse(TaxSetting.builder().mode(TaxMode.GROUP).build());

	}

}