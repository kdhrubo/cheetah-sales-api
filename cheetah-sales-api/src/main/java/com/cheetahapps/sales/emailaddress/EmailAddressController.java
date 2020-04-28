package com.cheetahapps.sales.emailaddress;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cheetahapps.sales.core.AbstractBaseController;

@RestController
@RequestMapping("/emailaddresses")
public class EmailAddressController extends AbstractBaseController<EmailAddress, String> {

	private final EmailAddressBusinessDelegate emailAddressBusinessDelegate;

	public EmailAddressController(EmailAddressBusinessDelegate emailAddressBusinessDelegate) {
		super(emailAddressBusinessDelegate);
		this.emailAddressBusinessDelegate = emailAddressBusinessDelegate;
	}

	@GetMapping("/q")
	public List<EmailAddress> searchAll(@RequestParam("rsql") String rsql) {
		return emailAddressBusinessDelegate.searchAll(rsql);
	}
}