package com.cheetahapps.sales.deal;

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
public class DealEventListener extends AbstractMongoEventListener<Deal> {

	private final MongoTemplate mongoTemplate;
	
	@Async
	@Override
	public void onAfterSave(AfterSaveEvent<Deal> event) {
		log.info("After save");
		
		mongoTemplate.save(event.getDocument(), "AuditDeal");
	}

	@Async
	@Override
	public void onAfterDelete(AfterDeleteEvent<Deal> event) {
		log.info("After delete");
		mongoTemplate.save(event.getDocument(), "AuditDeal");
		
	}
	
	

}
