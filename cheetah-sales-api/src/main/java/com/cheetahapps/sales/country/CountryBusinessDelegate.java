package com.cheetahapps.sales.country;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.event.ProvisionTenantEvent;

import com.github.rutledgepaulv.qbuilders.builders.GeneralQueryBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.visitors.MongoVisitor;
import com.github.rutledgepaulv.rqe.pipes.QueryConversionPipeline;

@Component
@Slf4j
public class CountryBusinessDelegate extends AbstractBusinessDelegate<Country, String> {

	private CountryRepository countryRepository;

	private QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();
	
	@Value("classpath:/data/countries.csv")
	private Resource resource;

	public CountryBusinessDelegate(CountryRepository repository) {
		super(repository);
		this.countryRepository = repository;
	}

	public Page<Country> search(String rsql, Pageable pageable) {
		log.debug("Searching category - {}", rsql);
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, Country.class);
		Criteria criteria = condition.query(new MongoVisitor());

		return countryRepository.search(criteria, pageable, Country.class);
	}
	

	@EventListener
	public void provision(ProvisionTenantEvent event) throws IOException{
		if (!event.isExistingTenant()) {
			load();
		}
	}
	
	public void load() throws IOException {

		try (Stream<String> stream = Files.lines(Paths.get(resource.getURI()))) {
			stream.forEach(i -> {
				String s [] = i.split(",");
				
				countryRepository.findByName(s[1]).onEmpty(() -> {
					
					Country country = Country.builder().code(s[0])
							.name(s[1]).build();
						
					this.save(country);
				});

			});
		}
	}

}
