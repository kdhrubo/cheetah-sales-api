package com.cheetahapps.sales.contact;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import com.cheetahapps.sales.core.AbstractBaseBusinessDelegate;
import com.github.rutledgepaulv.qbuilders.builders.GeneralQueryBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.visitors.MongoVisitor;
import com.github.rutledgepaulv.rqe.pipes.QueryConversionPipeline;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ContactBusinessDelegate extends AbstractBaseBusinessDelegate<Contact, String> {

	private ContactRepository repository;

	private QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();

	public ContactBusinessDelegate(ContactRepository repository) {
		super(repository);
		this.repository = repository;
	}

	public Page<Contact> search(String rsql, Pageable pageable) {
		// "firstName==Paul;age==30"
		// "deleted==false"
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, Contact.class);
		Criteria criteria = condition.query(new MongoVisitor());

		return repository.search(criteria, pageable, Contact.class);
	}

	public Contact addEmailAddress(String id, EmailAddress emailAddress) {
		Contact contact = null;
		Optional<Contact> ocontact = findById(id);
		if(ocontact.isPresent()) {
			contact = ocontact.get();
			List<EmailAddress> emailAddresses = contact.getEmailAddresses();
			
			if(emailAddresses == null) {
				emailAddresses = new ArrayList<>();
			}
			
			emailAddresses.add(emailAddress);
			contact.setEmailAddresses(emailAddresses);
			this.repository.save(contact);
			
			log.info("Email added to contact");
			
		}
		return contact;
	}
	
	public boolean addPhone(String id, Phone phone) {
		boolean saved = false;
		Optional<Contact> ocontact = findById(id);
		if(ocontact.isPresent()) {
			Contact contact = ocontact.get();
			List<Phone> phones = contact.getPhones();
			
			if(phones == null) {
				phones = new ArrayList<>();
				
			}
			phones.add(phone);
			contact.setPhones(phones);
			this.repository.save(contact);
			saved = true;
			
			log.info("Phone added to contact");
			
		}
		return saved;
	}
	
	public boolean addAddress(String id, Address address) {
		boolean saved = false;
		Optional<Contact> ocontact = findById(id);
		if(ocontact.isPresent()) {
			Contact contact = ocontact.get();
			List<Address> addresses = contact.getAddresses();
			
			if(addresses == null) {
				addresses = new ArrayList<>();
				
			}
			addresses.add(address);
			contact.setAddresses(addresses);
			this.repository.save(contact);
			saved = true;
			
			log.info("Address added to contact");
			
		}
		return saved;
	}

}