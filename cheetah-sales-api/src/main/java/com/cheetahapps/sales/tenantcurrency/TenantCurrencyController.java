package com.cheetahapps.sales.tenantcurrency;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cheetahapps.sales.core.AbstractController;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author jay
 * 
 */
@RestController
@RequestMapping("/tenantcurrencies")
@Slf4j
public class TenantCurrencyController extends AbstractController<TenantCurrency, String>{

	
	private final TenantCurrencyBusinessDelegate tenantCurrencyBusinessDelegate;
	
	public TenantCurrencyController(TenantCurrencyBusinessDelegate tenantCurrencyBusinessDelegate) {
		super(tenantCurrencyBusinessDelegate);
		this.tenantCurrencyBusinessDelegate = tenantCurrencyBusinessDelegate;
	}

	
	@GetMapping("/q")
	public List<TenantCurrency> searchAll(@RequestParam("rsql") String rsql) {
		log.debug("Search price book");
		return tenantCurrencyBusinessDelegate.searchAll(rsql);
	}
	
	@PostMapping("/add")
	public TenantCurrency add(@RequestBody TenantCurrency tenantCurrency) {
		log.info("## In add --> {}", tenantCurrency);
		return this.tenantCurrencyBusinessDelegate.add(tenantCurrency);
	}
	
	@PostMapping("/{id}/activate")
	public TenantCurrency activate(@PathVariable String id) {
		log.info("## In activate --> {}", id);
		return this.tenantCurrencyBusinessDelegate.activate(id);
	}
	
	@PostMapping("/{id}/setBase")
	public TenantCurrency makeBase(@PathVariable String id) {
		log.info("## In activate --> {}", id);
		return this.tenantCurrencyBusinessDelegate.makeBaseCurrency(id);
	}
	
	@PostMapping("/{id}/rate")
	public ConversionRate addConversionRate(@PathVariable String id,
			@RequestBody @Valid ConversionRate rate) {
		return this.tenantCurrencyBusinessDelegate.addConversionRate(id, rate);
		
		
	}
}
