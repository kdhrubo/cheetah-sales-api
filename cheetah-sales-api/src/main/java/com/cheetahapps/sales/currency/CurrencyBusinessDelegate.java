package com.cheetahapps.sales.currency;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;

import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.event.ProvisionTenantEvent;

@Component
@Slf4j
public class CurrencyBusinessDelegate extends AbstractBusinessDelegate<Currency, String> {

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
	public void provision(ProvisionTenantEvent event) throws IOException{
		if (!event.isExistingTenant()) {
			load();
		}
	}
	
	public void load() throws IOException {

		try (Stream<String> stream = Files.lines(Paths.get(resource.getURI()))) {
			stream.forEach(i -> {
				String s [] = i.split(",");
				
				currencyRepository.findByCountry(s[3]).onEmpty(() -> {
					
					Currency currency = Currency.builder().code(s[0])
							.symbol(s[1]).country(s[3]).name(s[2]).build();
						
					this.save(currency);
				});

			});
		}
	}

}
