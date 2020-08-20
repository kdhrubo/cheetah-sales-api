package com.cheetahapps.sales.charge;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheetahapps.sales.core.AbstractController;
import com.cheetahapps.sales.tax.Tax;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Rajiv
 *
 */

@RestController
@RequestMapping("/charge")
@Slf4j
public class ChargeController extends AbstractController<Charge, String> {

	private ChargeBusinessDelegate chargeBusinessDelegate;

	public ChargeController(ChargeBusinessDelegate chargeBusinessDelegate) {
		super(chargeBusinessDelegate);
		this.chargeBusinessDelegate = chargeBusinessDelegate;
	}
	
	@GetMapping("/search")
	public List<Charge> findAll() {
		return chargeBusinessDelegate.findAll();
	}

}
