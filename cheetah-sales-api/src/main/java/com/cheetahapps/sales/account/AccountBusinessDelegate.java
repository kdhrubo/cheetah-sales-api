package com.cheetahapps.sales.account;

import java.util.Arrays;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.event.ConvertLeadEvent;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
class AccountBusinessDelegate extends AbstractBusinessDelegate<Account, String> {
	
	private AccountRepository accountRepository;
	
	
	public AccountBusinessDelegate(AccountRepository repository) {
		super(repository);
		this.accountRepository = repository;
	}
	
	
	public Page<Account> search(String rsql, Pageable pageable) {
		return accountRepository.search(rsql, pageable, Account.class);
	}
	

	@EventListener
	public void handle(ConvertLeadEvent event) {
		
		Iterable<String> fields = Arrays.asList("email", 
				"otherEmail", "phone", "otherPhone", "mobile", "fax", 
				"primaryAddress", "secondaryAddress", "description", 
				"noOfEmployees", "annualRevenue",
				"website", "twitter", "facebook", "linkedin", 
				"donotCall", "emailOptIn", "smsOptIn"
				
				);
		
		if(event.isCreateAccount()) {
			Account account = Account.builder().build();
			
			BeanWrapper srcWrap = PropertyAccessorFactory.forBeanPropertyAccess(event.getLead());
			BeanWrapper trgWrap = PropertyAccessorFactory.forBeanPropertyAccess(account);
			
			fields.forEach(f -> trgWrap.setPropertyValue(f, srcWrap.getPropertyValue(f)));
			
			account.setName(event.getLead().getCompany());
			
			log.info("Converted account - {}", account);
			
			save(account);
			
		}
		
	}
	
}