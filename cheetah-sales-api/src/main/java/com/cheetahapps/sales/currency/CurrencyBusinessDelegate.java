package com.cheetahapps.sales.currency;

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
import com.cheetahapps.sales.event.ProvisionDefaultCurrencyEvent;
import com.cheetahapps.sales.event.ProvisionTenantEvent;

import io.vavr.control.Try;


@Component
@Slf4j
public class CurrencyBusinessDelegate extends AbstractBusinessDelegate<Currency, String> {
	
	@Value("${app.default.currency}")
	private String defaultCurrencyCode;

	private CurrencyRepository currencyRepository;

	
	@Value("classpath:/data/currency.csv")
	private Resource resource;

	public CurrencyBusinessDelegate(CurrencyRepository repository) {
		super(repository);
		this.currencyRepository = repository;
	}

	public List<Currency> searchAll(String rsql) {
		log.debug("Searching category - {}", rsql);

		return currencyRepository.searchAll(rsql, Currency.class);
	}
	

	@EventListener
	public void provision(ProvisionTenantEvent event) {
		if (!event.isExistingTenant()) {
			load();
			
			currencyRepository.findByCode(this.defaultCurrencyCode)
			.onEmpty(() -> log.warn("Default currency - {} not found. Cannot provision tenant currency.", this.defaultCurrencyCode))
			.peek(t -> publish(
					ProvisionDefaultCurrencyEvent.of(false, t.getCode()
							,t.getSymbol(), t.getName())
					));
			
		}
	}
	
	public void load() {
		
		Try.run(() ->

		saveAll(Files.lines(Paths.get(resource.getURI())).map(i -> i.split(","))
				.map(s -> Currency.builder().code(s[0])
						.symbol(s[1]).name(s[2]).build()).collect(Collectors.toList())))
				.onFailure(e -> log.info("Failed to provision currency. - {}", e.getMessage()))
				.onSuccess(t -> log.info("Currencies provisioned successfully."));

	}

}
