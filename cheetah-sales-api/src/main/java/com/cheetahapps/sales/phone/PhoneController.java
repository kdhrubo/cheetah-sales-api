package com.cheetahapps.sales.phone;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cheetahapps.sales.core.AbstractBaseController;

@RestController
@RequestMapping("/phones")
public class PhoneController extends AbstractBaseController<Phone, String> {

	private final PhoneBusinessDelegate phoneBusinessDelegate;

	public PhoneController(PhoneBusinessDelegate linkBusinessDelegate) {
		super(linkBusinessDelegate);
		this.phoneBusinessDelegate = linkBusinessDelegate;
	}

	@GetMapping("/q")
	public List<Phone> searchAll(@RequestParam("rsql") String rsql) {
		return phoneBusinessDelegate.searchAll(rsql);
	}
}