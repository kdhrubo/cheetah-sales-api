package com.cheetahapps.sales.country;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cheetahapps.sales.core.AbstractController;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/countries")
@Slf4j
public class CountryController extends AbstractController<Country, String>{

	
	private final CountryBusinessDelegate countryBusinessDelegate;
	
	public CountryController(CountryBusinessDelegate countryBusinessDelegate) {
		super(countryBusinessDelegate);
		this.countryBusinessDelegate = countryBusinessDelegate;
	}

	
	@GetMapping("/q")
	public Page<Country> search(@RequestParam("rsql") String rsql, Pageable pageable) {
		log.debug("Search currency");
		return countryBusinessDelegate.search(rsql, pageable);
	}
	
	
	

}
