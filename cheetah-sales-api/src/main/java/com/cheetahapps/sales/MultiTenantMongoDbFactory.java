package com.cheetahapps.sales;

import com.mongodb.client.MongoDatabase;

import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Slf4j
public class MultiTenantMongoDbFactory extends SimpleMongoClientDbFactory {

	public MultiTenantMongoDbFactory(String connectionString) {
		super(connectionString);
	}

	public String DEFAULT_DB = "cheetah-user-db";

	@Override
	public MongoDatabase getDb() throws DataAccessException {
		
		log.info("Getting DB");

		// Check the RequestContext
		Object tenant = RequestContextHolder.getRequestAttributes().getAttribute("tenantId",
				RequestAttributes.SCOPE_REQUEST);

		if (tenant instanceof String) {
			return getDb(tenant.toString());
		}
		
		log.info("returning - {}" , DEFAULT_DB);
		// Return a default DB
		return super.getDb(DEFAULT_DB);
	}

}