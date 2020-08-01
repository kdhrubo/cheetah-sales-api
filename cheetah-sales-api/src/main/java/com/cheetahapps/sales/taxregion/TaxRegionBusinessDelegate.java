package com.cheetahapps.sales.taxregion;

import org.springframework.stereotype.Component;
import com.cheetahapps.sales.core.AbstractBusinessDelegate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
class TaxRegionBusinessDelegate extends AbstractBusinessDelegate<TaxRegion, String> {

	private final TaxRegionRepository taxRegionRepository;

	public TaxRegionBusinessDelegate(TaxRegionRepository taxRegionRepository) {
		super(taxRegionRepository);
		this.taxRegionRepository = taxRegionRepository;
	}
	

	

}