package com.cheetahapps.sales.setting;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.event.ProvisionTenantEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SettingBusinessDelegate extends AbstractBusinessDelegate<Setting, String> {
	
	private SettingRepository settingRepository;

	public SettingBusinessDelegate(SettingRepository repository) {
		super(repository);
		this.settingRepository = repository;
	}
	
	@Transactional(readOnly = true)
	public Setting findByName(String name) {
		return this.settingRepository.findByName(name).get();
	}
	
	@EventListener
	@Transactional
	public void provision(ProvisionTenantEvent event) {
		if(!event.isExistingTenant()) {
			log.info("Provisoning mail setting.");
			save(event.getMailSetting());
		}
	}

}
