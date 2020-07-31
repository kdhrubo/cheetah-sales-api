package com.cheetahapps.sales.tax;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheetahapps.sales.core.AbstractController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/taxes")
@Slf4j
public class TaxController extends AbstractController<Tax, String> {

	private TaxBusinessDelegate taxBusinessDelegate;

	public TaxController(TaxBusinessDelegate taxBusinessDelegate) {
		super(taxBusinessDelegate);
		this.taxBusinessDelegate = taxBusinessDelegate;
	}

	@GetMapping("/q")
	public List<Tax> findAll() {
		return taxBusinessDelegate.findAll();
	}

}