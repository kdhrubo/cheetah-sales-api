package com.cheetahapps.sales.country;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.event.ProvisionTenantEvent;

import io.vavr.control.Try;

@Component
@Slf4j
public class CountryBusinessDelegate extends AbstractBusinessDelegate<Country, String> {

	private CountryRepository countryRepository;

	@Value("classpath:/data/countries.csv")
	private Resource resource;

	public CountryBusinessDelegate(CountryRepository repository) {
		super(repository);
		this.countryRepository = repository;
	}

	public List<Country> searchAll(String rsql) {
		log.debug("Searching category - {}", rsql);

		return countryRepository.searchAll(rsql, Country.class);
	}

	@EventListener
	public void provision(ProvisionTenantEvent event) {
		if (!event.isExistingTenant()) {
			load();
		}
	}

	public void load() {

		Try.run(() ->

		saveAll(Files.lines(Paths.get(resource.getURI())).map(i -> i.split(","))
				.map(s -> Country.builder().code(s[0]).name(s[1]).build()).collect(Collectors.toList())))
				.onFailure(e -> log.info("Failed to provision country. - {}", e.getMessage()))
				.onSuccess(t -> log.info("Countries provisioned successfully."));

	}

}
