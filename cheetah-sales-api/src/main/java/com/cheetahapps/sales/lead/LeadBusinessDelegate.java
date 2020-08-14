package com.cheetahapps.sales.lead;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.event.ConvertLeadEvent;
import com.cheetahapps.sales.problem.DeleteProblem;
import com.cheetahapps.sales.problem.NoDataFoundProblem;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
class LeadBusinessDelegate extends AbstractBusinessDelegate<Lead, String> {

	private final LeadRepository leadRepository;

	public LeadBusinessDelegate(LeadRepository repository) {
		super(repository);
		this.leadRepository = repository;
	}
	
	public Page<Lead> search(String rsql, Pageable pageable) {
		return leadRepository.search(rsql, pageable, Lead.class);
	}
	
	@Override
	protected void beforeSave(Lead lead) {
		//Future this will be rule driven, if no rule then assign default
		//Should post save later based on event. 
		
		//if new lead then only apply this
		if(getAuthUser() != null && lead.getId() == null) {
			
			lead.setAssignedTo(AssignedUser.of(getAuthUser().getUserId(), 
					getAuthUser().getFirstName(), 
					getAuthUser().getLastName(), 
					getAuthUser().getEmail()));
			
			lead.setSource("NIRVANA-CRM");
			
		}
	}

	@Override
	protected void beforeCopy(Lead lead) {
		lead.setFirstName("Copied " + lead.getFirstName());
		
	}
	
	

	@Override
	protected void beforeDelete(Lead lead) {
		if(lead.isConverted()) {
			new DeleteProblem("Converted lead cannot be deleted.");
		}
	}

	@Transactional
	public Lead convert(String id, boolean createDeal, boolean createAccount, boolean createContact) {
		Optional<Lead> lead = this.repository.findById(id);
		
		if (lead.isPresent()) {
			// update status
			log.info("Starting conversion of lead.");
			Lead l = lead.get();
			l.setConverted(true);
			repository.save(l);
			this.publish(ConvertLeadEvent.of(l, createDeal, createAccount, createContact));

			return l;
		} else {
			throw new NoDataFoundProblem("Lead with given id not found.");
		}

	}
	
	@Transactional
	public Lead addProduct(String id, Product product) {
		Optional<Lead> lead = this.repository.findById(id);
		if (lead.isPresent()) {
			Lead l = lead.get();
			List<Product> products = l.getProducts();
			
			if(products == null) {
				products = new ArrayList<>();
			}
			
			if(!products.contains(product)) {
				products.add(product);
				l.setProducts(products);
				
				this.repository.save(l);
			}
			
			
			
			
		} else {
			throw new NoDataFoundProblem("Lead with given id not found.");
		}

		return lead.get();
	}
	
	@Transactional
	public Lead removeProduct(String id, String productId) {
		Optional<Lead> lead = this.repository.findById(id);
		Product p = Product.builder().id(productId).build();
		if (lead.isPresent()) {
			Lead l = lead.get();
			List<Product> products = l.getProducts();
			
			if(products != null && products.contains(p)) {
				boolean status = products.remove(p);
				log.info("Product removed from lead - {}", status);
				
				if(status) {
					this.repository.save(l);
				}
			}
			
			
		} else {
			throw new NoDataFoundProblem("Lead with given id not found.");
		}

		return lead.get();
	}
	
	@Transactional
	public Lead addDocument(String id, DocumentItem docItem) {
		Optional<Lead> lead = this.repository.findById(id);
		if (lead.isPresent()) {
			Lead l = lead.get();
			List<DocumentItem> documents = l.getDocuments();
			
			if(documents == null) {
				documents = new ArrayList<>();
			}
			
			if(!documents.contains(docItem)) {
				documents.add(docItem);
				l.setDocuments(documents);
				
				this.repository.save(l);
			}
			
		} else {
			throw new NoDataFoundProblem("Lead with given id not found.");
		}

		return lead.get();
	}
	
	@Transactional
	public Lead removeDocument(String id, String docId) {
		Optional<Lead> lead = this.repository.findById(id);
		DocumentItem d = DocumentItem.builder().id(docId).build();
		if (lead.isPresent()) {
			Lead l = lead.get();
			List<DocumentItem> documents = l.getDocuments();
			log.info("Documents - {}", documents);
			
			if(documents != null && documents.contains(d)) {
				boolean status = documents.remove(d);
				log.info("Document removed from lead - {}", status);
				
				if(status) {
					this.repository.save(l);
				}
			}
			
			
		} else {
			throw new NoDataFoundProblem("Lead with given id not found.");
		}

		return lead.get();
	}
	
	@Transactional
	public Lead addNote(String id, Note note) {
		Lead lead = null;
		Optional<Lead> olead = findById(id);
		if (olead.isPresent()) {
			lead = olead.get();
			List<Note> notes = lead.getNotes();

			if (notes == null) {
				notes = new ArrayList<>();

			}
			User u = User.builder().email(getAuthUser().getEmail())
					.firstName(getAuthUser().getFirstName()).lastName(getAuthUser().getLastName())
					.userId(getAuthUser().getUserId()).build();
			note.setCreatedBy(u);
			note.setCreatedDate(LocalDateTime.now());
			notes.add(note);
			lead.setNotes(notes);
			this.save(lead);

			log.info("Note added to Lead");

		}
		return lead;
	}

}