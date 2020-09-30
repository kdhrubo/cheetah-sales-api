package com.cheetahapps.sales.country;

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
@RequestMapping("/countries")
@Slf4j
public class CountryController extends AbstractController<Country, String> implements CountryControllerInterface{

	
	private final CountryBusinessDelegate countryBusinessDelegate;
	
	public CountryController(CountryBusinessDelegate countryBusinessDelegate) {
		super(countryBusinessDelegate);
		this.countryBusinessDelegate = countryBusinessDelegate;
	}

	
	@Override
	@GetMapping("/q")
	public List<Country> searchAll(@RequestParam("rsql") String rsql) {
		log.debug("Search currency");
		return countryBusinessDelegate.searchAll(rsql);
	}
	
	
	

}
