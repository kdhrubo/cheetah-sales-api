package com.cheetahapps.sales.vendor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.problem.NoDataFoundProblem;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
class VendorBusinessDelegate extends AbstractBusinessDelegate<Vendor, String> {

	private final VendorRepository vendorRepository;

	public VendorBusinessDelegate(VendorRepository vendorRepository) {
		super(vendorRepository);
		this.vendorRepository = vendorRepository;
	}
	
	public Page<Vendor> search(String rsql, Pageable pageable) {
		return vendorRepository.search(rsql, pageable, Vendor.class);
	}
	
	@Override
	protected void beforeSave(Vendor vendor) {
		//Future this will be rule driven, if no rule then assign default
		//Should post save later based on event. 
		
		//if new lead then only apply this
		if(getAuthUser() != null && vendor.getId() == null) {
			
			vendor.setAssignedTo(AssignedUser.of(getAuthUser().getUserId(), 
					getAuthUser().getFirstName(), 
					getAuthUser().getLastName(), 
					getAuthUser().getEmail()));
			
			vendor.setSource("NIRVANA-CRM");
			
		}
	}

	
	@Transactional
	public Vendor addProduct(String id, Product product) {
		Optional<Vendor> vendor = this.repository.findById(id);
		if (vendor.isPresent()) {
			Vendor v = vendor.get();
			List<Product> products = v.getProducts();
			
			if(products == null) {
				products = new ArrayList<>();
			}
			
			if(!products.contains(product)) {
				products.add(product);
				v.setProducts(products);
				
				this.repository.save(v);
			}
			
			
			return v;
			
		} else {
			throw new NoDataFoundProblem("Vendor with given id not found.");
		}

		
	}
	
	@Transactional
	public Vendor removeProduct(String id, String productId) {
		Optional<Vendor> vendor = this.repository.findById(id);
		Product p = Product.builder().id(productId).build();
		if (vendor.isPresent()) {
			Vendor v = vendor.get();
			List<Product> products = v.getProducts();
			
			if(products != null && products.contains(p)) {
				boolean status = products.remove(p);
				log.info("Product removed from vendor - {}", status);
				
				if(status) {
					this.repository.save(v);
				}
			}
			return v;
			
		} else {
			throw new NoDataFoundProblem("Vendor with given id not found.");
		}

		
	}
	
	@Transactional
	public Vendor addContact(String id, Contact contact) {
		Optional<Vendor> vendor = this.repository.findById(id);
		if (vendor.isPresent()) {
			Vendor v = vendor.get();
			List<Contact> contacts = v.getContacts();
			
			if(contacts == null) {
				contacts = new ArrayList<>();
			}
			
			if(!contacts.contains(contact)) {
				contacts.add(contact);
				v.setContacts(contacts);
				
				this.repository.save(v);
			}
			
			return v;
			
		} else {
			throw new NoDataFoundProblem("Vendor with given id not found.");
		}

		
	}
	
	@Transactional
	public Vendor removeContact(String id, String customerId) {
		Optional<Vendor> vendor = this.repository.findById(id);
		Contact c = Contact.builder().id(customerId).build();
		if (vendor.isPresent()) {
			Vendor v = vendor.get();
			List<Contact> contacts = v.getContacts();
			
			if(contacts != null && contacts.contains(c)) {
				boolean status = contacts.remove(c);
				log.info("Document removed from vendor - {}", status);
				
				if(status) {
					this.repository.save(v);
				}
			}
			
			return v;
			
			
		} else {
			throw new NoDataFoundProblem("Lead with given id not found.");
		}

	}
	

}