package com.cheetahapps.sales.integration.box;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.vavr.control.Option;


interface BoxSettingRepository extends MongoRepository<BoxSetting, String> {
	
	Option<BoxSetting> findFirstByDeleted(boolean deleted);
	
}
