package com.cheetahapps.sales.currency;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cheetahapps.sales.core.AbstractController;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/currencies")
@Slf4j
public class CurrencyController extends AbstractController<Currency, String>{

	
	private final CurrencyBusinessDelegate currencyBusinessDelegate;
	
	public CurrencyController(CurrencyBusinessDelegate currencyBusinessDelegate) {
		super(currencyBusinessDelegate);
		this.currencyBusinessDelegate = currencyBusinessDelegate;
	}

	
	@GetMapping("/q")
	public List<Currency> search(@RequestParam("rsql") String rsql) {
		log.debug("Search currency");
		return currencyBusinessDelegate.searchAll(rsql);
	}
	

}
