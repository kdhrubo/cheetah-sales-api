package com.cheetahapps.sales.business;

import org.springframework.stereotype.Component;

import com.cheetahapps.sales.domain.Account;
import com.cheetahapps.sales.repository.AccountRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AccountBusinessDelegate extends AbstractBaseBusinessDelegate<Account, String> {
	
	private AccountRepository repository;
	
	public AccountBusinessDelegate(AccountRepository repository) {
		super(repository);
		this.repository = repository;
	}
	

}