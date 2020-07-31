package com.cheetahapps.sales.tax;


import java.util.List;

import org.springframework.stereotype.Component;


import com.cheetahapps.sales.core.AbstractBusinessDelegate;



import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
class TaxBusinessDelegate extends AbstractBusinessDelegate<Tax, String> {

	private final TaxRepository taxRepository;

	public TaxBusinessDelegate(TaxRepository taxRepository) {
		super(taxRepository);
		this.taxRepository = taxRepository;
	}
	
	public List<Tax> findAll() {
		return taxRepository.findAll();
	}
	
	

}