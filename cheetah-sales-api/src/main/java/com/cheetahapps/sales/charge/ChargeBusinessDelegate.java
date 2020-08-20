package com.cheetahapps.sales.charge;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;

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

}
