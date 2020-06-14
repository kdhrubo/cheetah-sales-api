package com.cheetahapps.sales.setting;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.vavr.control.Option;

interface SettingRepository extends MongoRepository<Setting, String>{
	
	Option<Setting> findByName(String name);
	
}
