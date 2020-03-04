package com.cheetahapps.sales.business;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cheetahapps.sales.domain.Contact;
import com.effectiv.crm.repository.jpa.ContactRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ContactBusinessDelegate extends AbstractBaseBusinessDelegate<Contact, String> {

	

	private ContactRepository repository;

	public ContactBusinessDelegate(ContactRepository repository) {
		super(repository);
		this.repository = repository;
	}

	public List<Contact> findByFirstNameLike(String firstName) {
		log.debug("Find by first name - {}", firstName);
		return repository.findByFirstNameLike(firstName);
	}

}