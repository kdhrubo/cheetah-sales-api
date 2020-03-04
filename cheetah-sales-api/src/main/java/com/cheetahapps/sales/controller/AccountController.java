package com.cheetahapps.sales.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.cheetahapps.sales.business.AccountBusinessDelegate;
import com.cheetahapps.sales.domain.Account;

@Controller
@RequestMapping("/accounts")

public class AccountController extends AbstractBaseController<Account, String> {

	@Autowired
	public AccountController(AccountBusinessDelegate service) {
		super(service);
	}

}