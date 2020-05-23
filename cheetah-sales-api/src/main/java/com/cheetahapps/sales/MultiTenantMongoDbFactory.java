package com.cheetahapps.sales;

import com.mongodb.client.MongoDatabase;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Slf4j
public class MultiTenantMongoDbFactory extends SimpleMongoClientDatabaseFactory {

	public MultiTenantMongoDbFactory(String connectionString) {
		super(connectionString);
	}

	public String DEFAULT_DB = "coredb";

	@Override
	public MongoDatabase getMongoDatabase()  {

		log.info("Getting Mongo DB");

		// Check the RequestContext
		if (RequestContextHolder.getRequestAttributes() != null) {
			Object tenant = RequestContextHolder.getRequestAttributes().getAttribute("tenantId",
					RequestAttributes.SCOPE_REQUEST);

			if (tenant instanceof String) {
				return getMongoDatabase(tenant.toString());
			}

		}

		log.info("returning - {}", DEFAULT_DB);
		// Return a default DB
		MongoDatabase db = getMongoDatabase(DEFAULT_DB);
		
		return db;
	}

	@Override
	protected String getDefaultDatabaseName() {

		return DEFAULT_DB;
	}

}