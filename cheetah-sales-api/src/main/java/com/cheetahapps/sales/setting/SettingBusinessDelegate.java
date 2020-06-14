package com.cheetahapps.sales.setting;

import org.springframework.stereotype.Service;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;


@Service
public class SettingBusinessDelegate extends AbstractBusinessDelegate<Setting, String> {
	
	private SettingRepository settingRepository;

	public SettingBusinessDelegate(SettingRepository repository) {
		super(repository);
		this.settingRepository = repository;
	}
	
	public Setting findByName(String name) {
		return this.settingRepository.findByName(name).get();
	}

}
