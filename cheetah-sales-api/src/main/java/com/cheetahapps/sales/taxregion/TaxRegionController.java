package com.cheetahapps.sales.taxregion;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheetahapps.sales.core.AbstractController;


import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/taxes/region")
@Slf4j
public class TaxRegionController extends AbstractController<TaxRegion, String> {

	private TaxRegionBusinessDelegate taxRegionBusinessDelegate;

	public TaxRegionController(TaxRegionBusinessDelegate taxRegionBusinessDelegate) {
		super(taxRegionBusinessDelegate);
		this.taxRegionBusinessDelegate = taxRegionBusinessDelegate;
	}

	

}