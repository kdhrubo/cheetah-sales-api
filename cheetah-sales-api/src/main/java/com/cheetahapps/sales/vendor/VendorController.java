package com.cheetahapps.sales.vendor;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cheetahapps.sales.core.AbstractController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/leads")
@Slf4j
public class VendorController extends AbstractController<Vendor, String> {

	private VendorBusinessDelegate vendorBusinessDelegate;

	public VendorController(VendorBusinessDelegate vendorBusinessDelegate) {
		super(vendorBusinessDelegate);
		this.vendorBusinessDelegate = vendorBusinessDelegate;
	}

	@GetMapping("/q")
	public Page<Vendor> search(@RequestParam("rsql") String rsql, @PageableDefault Pageable pageable) {
		return vendorBusinessDelegate.search(rsql, pageable);
	}

	@PostMapping("/import")
	public List<Vendor> saveAll(@RequestBody Vendors vendors) {
		log.info("## In saveAll --> {}", vendors);

		return vendorBusinessDelegate.saveAll(vendors.getVendors());
	}

	
	@PostMapping("/{id}/products")
	public Vendor addProduct(@PathVariable String id, @RequestBody @Valid Product product) {
		return this.vendorBusinessDelegate.addProduct(id, product);
	}
	
	@PostMapping("/{id}/products/{productId}")
	public Vendor removeProduct(@PathVariable String id, @PathVariable String productId) {
		return this.vendorBusinessDelegate.removeProduct(id, productId);
	}
	
	@PostMapping("/{id}/contacts")
	public Vendor addDocument(@PathVariable String id, @RequestBody @Valid Contact contact) {
		return this.vendorBusinessDelegate.addContact(id, contact);
	}
	
	@PostMapping("/{id}/contacts/{contactId}")
	public Vendor removeDocument(@PathVariable String id, @PathVariable String contactId) {
		log.info("Removing contact from vendor.");
		return this.vendorBusinessDelegate.removeContact(id, contactId);
	}
}