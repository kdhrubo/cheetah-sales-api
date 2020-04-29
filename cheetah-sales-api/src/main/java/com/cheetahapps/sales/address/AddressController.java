package com.cheetahapps.sales.address;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cheetahapps.sales.core.AbstractBaseController;

@RestController
@RequestMapping("/postal")
public class AddressController extends AbstractBaseController<Address, String> {

	private final AddressBusinessDelegate addressBusinessDelegate;

	public AddressController(AddressBusinessDelegate emailAddressBusinessDelegate) {
		super(emailAddressBusinessDelegate);
		this.addressBusinessDelegate = emailAddressBusinessDelegate;
	}

	@GetMapping("/q")
	public List<Address> searchAll(@RequestParam("rsql") String rsql) {
		return addressBusinessDelegate.searchAll(rsql);
	}
}