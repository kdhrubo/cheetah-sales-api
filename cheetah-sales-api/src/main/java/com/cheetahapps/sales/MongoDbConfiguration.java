package com.cheetahapps.sales;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.cheetahapps.sales.security.SecurityAuditorAware;

@Configuration
@EnableMongoAuditing
public class MongoDbConfiguration {

	@Value("${app.mongodb.uri}")
	private String mongoUri;

	@Bean
	@Primary
	public MongoDatabaseFactory mongoDbFactory() {

		return new MultiTenantMongoDbFactory(mongoUri);
	}

	@Bean
	@Primary
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoDbFactory());
	}

	@Bean
	public AuditorAware<String> auditorProvider() {
		return new SecurityAuditorAware();
	}
}