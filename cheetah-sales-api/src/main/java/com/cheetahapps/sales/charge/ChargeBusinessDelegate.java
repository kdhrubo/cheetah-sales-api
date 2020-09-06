package com.cheetahapps.sales.charge;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cheetahapps.sales.category.Category;
import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.lead.Lead;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Rajiv
 *
 */

@Slf4j
@Service
public class ChargeBusinessDelegate extends AbstractBusinessDelegate<Charge, String> {

	private ChargeRepository chargeRepository;

	public ChargeBusinessDelegate(ChargeRepository chargeRepository) {
		super(chargeRepository);
		this.chargeRepository = chargeRepository;
	}

	public List<Charge> findAll() {
		return chargeRepository.findAll();
	}
	
	public Page<Charge> search(String rsql, Pageable pageable) {
		log.debug("Searching for charge");
		return chargeRepository.search(rsql, pageable, Charge.class);
	}
	


}
