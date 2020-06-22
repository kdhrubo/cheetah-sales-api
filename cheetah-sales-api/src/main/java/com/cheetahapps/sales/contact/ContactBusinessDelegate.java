package com.cheetahapps.sales.contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.lead.Lead;
import com.github.rutledgepaulv.qbuilders.builders.GeneralQueryBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.visitors.MongoVisitor;
import com.github.rutledgepaulv.rqe.pipes.QueryConversionPipeline;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ContactBusinessDelegate extends AbstractBusinessDelegate<Contact, String> {

	private ContactRepository contactRepository;

	private QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();

	public ContactBusinessDelegate(ContactRepository repository) {
		super(repository);
		this.contactRepository = repository;
	}

	public Page<Contact> search(String rsql, Pageable pageable) {
		// "firstName==Paul;age==30"
		// "deleted==false"
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, Contact.class);
		Criteria criteria = condition.query(new MongoVisitor());

		return contactRepository.search(criteria, pageable, Contact.class);
	}
	
	@Override
	protected void beforeCopy(Contact toBecopied) {
		toBecopied.setFirstName("Copied " + toBecopied.getFirstName());
		
	}

	public Contact addAddress(String id, Address address) {
		Contact contact = null;
		Optional<Contact> ocontact = findById(id);
		if (ocontact.isPresent()) {
			contact = ocontact.get();
			List<Address> addresses = contact.getAddresses();

			if (addresses == null) {
				addresses = new ArrayList<>();

			}
			addresses.add(address);
			contact.setAddresses(addresses);
			this.save(contact);

			log.info("Address added to contact");

		}
		return contact;
	}

	public Contact addNote(String id, Note note) {
		Contact contact = null;
		Optional<Contact> ocontact = findById(id);
		if (ocontact.isPresent()) {
			contact = ocontact.get();
			List<Note> notes = contact.getNotes();

			if (notes == null) {
				notes = new ArrayList<>();

			}
			notes.add(note);
			contact.setNotes(notes);
			this.save(contact);

			log.info("Note added to contact");

		}
		return contact;
	}

}