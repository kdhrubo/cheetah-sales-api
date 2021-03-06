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
	
	
	@PostMapping("/{id}/notes")
	public Contact addNotes(@PathVariable String id, @RequestBody @Valid Note note) {
		return this.contactBusinessDelegate.addNote(id, note);
	}

}