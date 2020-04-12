package com.cheetahapps.sales.lead;

import java.util.List;

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

import com.cheetahapps.sales.core.AbstractBaseController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/leads")
@Slf4j
public class LeadController extends AbstractBaseController<Lead, String> {

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

	@PostMapping("/convert/{id}")
	public Lead convert(@PathVariable String id,
			@RequestParam(name = "createDeal", defaultValue = "false") boolean createDeal,
			@RequestParam(name = "createAccount", defaultValue = "false") boolean createAccount,
			@RequestParam(name = "createContact", defaultValue = "false") boolean createContact) {

		return null;
	}
}