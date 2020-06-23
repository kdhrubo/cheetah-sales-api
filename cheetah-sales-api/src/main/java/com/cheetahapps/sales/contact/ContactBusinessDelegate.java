package com.cheetahapps.sales.contact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.event.ConvertLeadEvent;
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
	
	@EventListener
	public void createFromLead(ConvertLeadEvent event) {
		//field mapping to come from db 
		
		Iterable<String> fields = Arrays.asList(
				
				"phone", "otherPhone","fax", 
				"email", "otherEmail",
				  "mobile", 
				"primaryAddress", "secondaryAddress", "description", "salutationId","salutation"
				,"firstName","lastName", "designationId", "designation", "leadSourceId", "leadSource",
				"twitter", "facebook", "linkedin", "donotCall", "emailOptIn", "smsOptIn","notifyOwner"
				);

		
		if(event.isCreateContact()) {
			Contact contact = Contact.builder().build();
			
			BeanWrapper srcWrap = PropertyAccessorFactory.forBeanPropertyAccess(event.getLead());
			BeanWrapper trgWrap = PropertyAccessorFactory.forBeanPropertyAccess(contact);
			
			fields.forEach(f -> trgWrap.setPropertyValue(f, srcWrap.getPropertyValue(f)));
			log.info("Converted contact - {}", contact);
			
			save(contact);
		}
		
		
	}

	@Transactional
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