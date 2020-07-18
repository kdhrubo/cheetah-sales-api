package com.cheetahapps.sales.tenantcurrency;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.event.ProvisionDefaultCurrencyEvent;
import com.cheetahapps.sales.problem.DuplicateDataProblem;
import com.cheetahapps.sales.problem.NoDataFoundProblem;

import io.vavr.control.Option;

/**
 * 
 * @author jay
 * @Description: Performs business logic for creation of TenantCurrency
 */
@Component
@Slf4j
public class TenantCurrencyBusinessDelegate extends AbstractBusinessDelegate<TenantCurrency, String> {
	

	private TenantCurrencyRepository tenantCurrencyRepository;

	public TenantCurrencyBusinessDelegate(TenantCurrencyRepository repository) {
		super(repository);
		this.tenantCurrencyRepository = repository;
	}
	
	@EventListener
	public void provision(ProvisionDefaultCurrencyEvent event) {
		if (!event.isExistingTenant()) {
			
			save(TenantCurrency.builder()
					.active(true).code(event.getCode())
					.corporate(true)
					.name(event.getName())
					.symbol(event.getSymbol()).build());
		}
	}

	@Transactional(readOnly = true)
	public List<TenantCurrency> searchAll(String rsql) {
		log.debug("Searching category - {}", rsql);

		return tenantCurrencyRepository.searchAll(rsql, TenantCurrency.class);
	}

	@Transactional
	public TenantCurrency add(TenantCurrency tenantCurrency) {
		// check if currency already added.
		Option<TenantCurrency> tc = tenantCurrencyRepository.findByCode(tenantCurrency.getCode());

		if (tc.isEmpty()) {
			return save(tenantCurrency);
		} else {
			// throw error
			throw new DuplicateDataProblem("Currency already added.");
		}
	}

	@Transactional
	public ConversionRate addConversionRate(String id, @Valid ConversionRate rate) {
		Optional<TenantCurrency> tc = findById(id);

		if (tc.isPresent()) {
			TenantCurrency tenantCurrency = tc.get();
			List<ConversionRate> rates = tenantCurrency.getConversionRates();

			if (rates == null) { // first time
				rates = new ArrayList<>();

			} else { // existing
						// find the active rate first

				Optional<ConversionRate> filteredRate = rates.stream().filter(i -> i.getTo() == null).findFirst();

				if (filteredRate.isPresent()) {
					ConversionRate activeRate = filteredRate.get();
					activeRate.setTo(LocalDateTime.now()); // set end
				}

			}

			rate.setFrom(LocalDateTime.now());
			rates.add(rate);
			tenantCurrency.setConversionRates(rates);
			save(tenantCurrency);
		} else {
			throw new NoDataFoundProblem("Tenant currency not found.");
		}

		return rate;
	}
	
	@Transactional
	public TenantCurrency activate(String id) {
		Optional<TenantCurrency> tc = findById(id);
		TenantCurrency tenantCurrency = null;
		if (tc.isPresent()) {
			tenantCurrency = tc.get();
			tenantCurrency.setActive(true);
			save(tenantCurrency);
		} else {
			throw new NoDataFoundProblem("Tenant currency not found.");
		}

		return tenantCurrency;
	}
	
	@Transactional
	public TenantCurrency makeBaseCurrency(String id) {
		//get all currencies and disable the current 
		Optional<TenantCurrency> currency = this.tenantCurrencyRepository.findAll().stream()
				.filter(i -> i.isCorporate()).findFirst();
		
		
		if(currency.isPresent()) {
			TenantCurrency tc = currency.get();
			tc.setCorporate(false);
			save(tc);
		}
		
		
		Optional<TenantCurrency> tc = findById(id);
		TenantCurrency tenantCurrency = null;
		if (tc.isPresent()) {
			tenantCurrency = tc.get();
			tenantCurrency.setActive(true);
			tenantCurrency.setCorporate(true);
			save(tenantCurrency);
		} else {
			throw new NoDataFoundProblem("Tenant currency not found.");
		}

		return tenantCurrency;
	}

}
