package com.cheetahapps.sales.contact;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.cheetahapps.sales.core.AbstractBaseController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/contacts")
@Slf4j
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
	
	@PostMapping("/import")
	public List<Contact> saveAll(@RequestBody ContactList contactWrapper) {
		log.info("## In saveAll --> {}", contactWrapper);
		
		return this.contactBusinessDelegate.saveAll(contactWrapper.getContacts());
	}

}