package com.cheetahapps.sales.tenantcurrency;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/tenantcurrency")
@Slf4j
public class TenantCurrencyController extends AbstractController<TenantCurrency, String>{

	
	private final TenantCurrencyBusinessDelegate tenantCurrencyBusinessDelegate;
	
	public TenantCurrencyController(TenantCurrencyBusinessDelegate tenantCurrencyBusinessDelegate) {
		super(tenantCurrencyBusinessDelegate);
		this.tenantCurrencyBusinessDelegate = tenantCurrencyBusinessDelegate;
	}

	
	@GetMapping("/q")
	public Page<TenantCurrency> search(@RequestParam("rsql") String rsql, Pageable pageable) {
		log.debug("Search price book");
		return tenantCurrencyBusinessDelegate.search(rsql, pageable);
	}
	
	@Deprecated
	@PostMapping("/savelist")
	public List<TenantCurrency> saveAll(@RequestBody TenantCurrencyList tenantCurrencyWrapper) {
		log.info("## In saveAll --> {}", tenantCurrencyWrapper);
		
		return this.tenantCurrencyBusinessDelegate.saveAll(tenantCurrencyWrapper.getTenantCurrency());
	}
	

}
