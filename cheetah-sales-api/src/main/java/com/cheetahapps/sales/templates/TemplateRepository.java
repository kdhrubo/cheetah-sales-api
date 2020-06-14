package com.cheetahapps.sales.templates;


import org.springframework.data.mongodb.repository.MongoRepository;

import io.vavr.control.Option;


interface TemplateRepository extends MongoRepository<Template, String>{
	
	//Page<MailTemplate> findByDeleted(boolean delete, Page page);
	
	Option<Template> findByName(String name);
}
