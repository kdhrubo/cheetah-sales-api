package com.cheetahapps.sales;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.net.UnknownHostException;

@Configuration
public class MongoDbConfiguration {

	@Value("${app.mongodb.uri}")
	private String mongoUri;

	@Bean
	@Primary
	public MongoDbFactory mongoDbFactory() throws UnknownHostException {

		return new MultiTenantMongoDbFactory(mongoUri);
	}

	@Bean
	@Primary
	public MongoTemplate mongoTemplate() throws UnknownHostException {
		return new MongoTemplate(mongoDbFactory());
	}
}