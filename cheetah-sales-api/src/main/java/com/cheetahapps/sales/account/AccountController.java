package com.cheetahapps.sales.account;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.cheetahapps.sales.controller.AbstractBaseController;

@RestController
@RequestMapping("/accounts")
public class AccountController extends AbstractBaseController<Account, String> {
	
	private final AccountBusinessDelegate accountBusinessDelegate;

	@Autowired
	public AccountController(AccountBusinessDelegate accountBusinessDelegate) {
		super(accountBusinessDelegate);
		this.accountBusinessDelegate = accountBusinessDelegate;
	}
	
	@GetMapping("/q")
	public Page<Account> search(@RequestParam("rsql") String rsql, Pageable pageable) {
		return accountBusinessDelegate.search(rsql, pageable);
	}
}