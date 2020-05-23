package com.cheetahapps.sales.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.cheetahapps.sales.core.AbstractController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/accounts")
class AccountController extends AbstractController<Account, String> {
	
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
	
	@PostMapping("/import")
	public List<Account> saveAll(@RequestBody AccountList accountWrapper) {
		log.info("## Saving all leads by import api  --> {}", accountWrapper);
		return accountBusinessDelegate.saveAll(accountWrapper.getAccounts());
	}
}