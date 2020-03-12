package com.cheetahapps.sales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.cheetahapps.sales.business.ContactBusinessDelegate;

import com.cheetahapps.sales.domain.Contact;

@RestController
@RequestMapping("/contacts")

public class ContactController extends AbstractBaseController<Contact, String> {

	private ContactBusinessDelegate contactBusinessDelegate;

	@Autowired
	public ContactController(ContactBusinessDelegate contactBusinessDelegate) {
		super(contactBusinessDelegate);
		this.contactBusinessDelegate = contactBusinessDelegate;
	}

	@GetMapping("/q")
	public Page<Contact> search(@RequestParam("rsql") String rsql, Pageable pageable) {
		return contactBusinessDelegate.search(rsql, pageable);
	}

}