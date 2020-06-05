package com.cheetahapps.sales.contact;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.cheetahapps.sales.core.AbstractController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/contacts")
@Slf4j
class ContactController extends AbstractController<Contact, String> {

	private ContactBusinessDelegate contactBusinessDelegate;

	public ContactController(ContactBusinessDelegate contactBusinessDelegate) {
		super(contactBusinessDelegate);
		this.contactBusinessDelegate = contactBusinessDelegate;
	}

	@GetMapping("/q")
	public Page<Contact> search(@RequestParam("rsql") String rsql, Pageable pageable) {
		return contactBusinessDelegate.search(rsql, pageable);
	}
	
	@PostMapping("/import")
	public List<Contact> saveAll(@RequestBody Contacts contactWrapper) {
		log.info("## In saveAll --> {}", contactWrapper);
		
		return this.contactBusinessDelegate.saveAll(contactWrapper.getContacts());
	}
	
	
	@PostMapping("/{id}/emails")
	public Contact addEmailAddress(@PathVariable String id, @RequestBody @Valid Emails emails) {
		return this.contactBusinessDelegate.addEmailAddressNew(id, emails);
	}
	
	@PostMapping("/{id}/phones")
	public Contact addPhone(@PathVariable String id, @RequestBody @Valid Phone phone) {
		return this.contactBusinessDelegate.addPhone(id, phone);
	}
	
	@PostMapping("/{id}/addresses")
	public Contact addAddress(@PathVariable String id, @RequestBody @Valid Address address) {
		return this.contactBusinessDelegate.addAddress(id, address);
	}
	
	@PostMapping("/{id}/links")
	public Contact addLinks(@PathVariable String id, @RequestBody @Valid Link link) {
		return this.contactBusinessDelegate.addLink(id, link);
	}
	
	@PostMapping("/{id}/notes")
	public Contact addNotes(@PathVariable String id, @RequestBody @Valid Note note) {
		return this.contactBusinessDelegate.addNote(id, note);
	}

}