package com.cheetahapps.sales.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cheetahapps.sales.business.LeadBusinessDelegate;
import com.cheetahapps.sales.domain.Lead;
import com.cheetahapps.sales.domain.LeadWrapper;
import com.cheetahapps.sales.dto.LeadConversionDto;

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

	// TODO - Complete implementation
	@PostMapping("/convert")
	public Lead convertSubmit(@RequestBody LeadConversionDto leadConversionDto) {
		log.info("## holder --> {}", leadConversionDto);

		return null;
	}
	
	@GetMapping("/q")
	public Page<Lead> search(@RequestParam("rsql") String rsql, @PageableDefault Pageable pageable) {
		log.info("Lead business delegate - {}", this.businessDelegate);
		log.info("rsql - {}", rsql);
		log.info("pageable - {}", pageable);
		return leadBusinessDelegate.search(rsql, pageable);
	}
	
	@PostMapping("/saveAll")
	public List<Lead> saveAll(@RequestBody LeadWrapper leadWrapper) {
		log.info("## In saveAll --> {}", leadWrapper);
		
		return leadBusinessDelegate.saveAll(leadWrapper);
	}
}