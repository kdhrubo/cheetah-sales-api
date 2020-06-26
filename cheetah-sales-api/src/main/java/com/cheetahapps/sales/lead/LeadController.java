package com.cheetahapps.sales.lead;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class LeadController extends AbstractController<Lead, String> {

	private LeadBusinessDelegate leadBusinessDelegate;

	public LeadController(LeadBusinessDelegate leadBusinessDelegate) {
		super(leadBusinessDelegate);
		this.leadBusinessDelegate = leadBusinessDelegate;
	}

	@GetMapping("/q")
	public Page<Lead> search(@RequestParam("rsql") String rsql, @PageableDefault Pageable pageable) {
		log.info("Lead business delegate - {}", this.businessDelegate);
		log.info("rsql - {}", rsql);
		log.info("pageable - {}", pageable);
		return leadBusinessDelegate.search(rsql, pageable);
	}

	@PostMapping("/import")
	public List<Lead> saveAll(@RequestBody LeadList leadWrapper) {
		log.info("## In saveAll --> {}", leadWrapper);

		return leadBusinessDelegate.saveAll(leadWrapper.getLeads());
	}

	@PostMapping("/convert")
	public Lead convert(@RequestBody @Valid ConvertLeadRequest request) {
		log.info("Starting lead coversion process to deal");
		return leadBusinessDelegate.convert(request.getId(), request.isCreateDeal(), 
				request.isCreateAccount(), request.isCreateContact());
	}
	
	@PostMapping("/{id}/products")
	public Lead addProduct(@PathVariable String id, @RequestBody @Valid Product product) {
		return this.leadBusinessDelegate.addProduct(id, product);
	}
	
	@PostMapping("/{id}/products/{productId}")
	public Lead removeProduct(@PathVariable String id, @PathVariable String productId) {
		return this.leadBusinessDelegate.removeProduct(id, productId);
	}
}