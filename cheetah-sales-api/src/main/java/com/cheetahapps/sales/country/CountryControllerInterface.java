package com.cheetahapps.sales.country;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

public interface CountryControllerInterface  {
	
	
	List<Country> searchAll(String rsql);

}