package com.cheetahapps.sales.account;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class AccountEventListener extends AbstractMongoEventListener<Account> {

	private final MongoTemplate mongoTemplate;
	
	@Async
	@Override
	public void onAfterSave(AfterSaveEvent<Account> event) {
		log.info("After save");
		
		mongoTemplate.save(event.getDocument(), "AuditAccount");
	}

	@Async
	@Override
	public void onAfterDelete(AfterDeleteEvent<Account> event) {
		log.info("After delete");
		mongoTemplate.save(event.getDocument(), "AuditAccount");
		
	}
	
	

}
