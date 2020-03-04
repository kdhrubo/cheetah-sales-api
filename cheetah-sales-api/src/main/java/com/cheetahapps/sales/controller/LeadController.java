package com.cheetahapps.sales.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.cheetahapps.sales.business.LeadBusinessDelegate;
import com.cheetahapps.sales.domain.Lead;
import com.cheetahapps.sales.dto.LeadConversionDto;

@Controller
@RequestMapping("/lead")
@Slf4j
public class LeadController extends AbstractBaseController<Lead, String> {

	private LeadBusinessDelegate businessDelegate;

	public LeadController(LeadBusinessDelegate businessDelegate) {
		super(businessDelegate);
		this.businessDelegate = businessDelegate;
	}

	// TODO - Complete implementation
	@PostMapping("/convert")
	public Lead convertSubmit(@RequestBody LeadConversionDto leadConversionDto) {
		log.info("## holder --> {}", leadConversionDto);

		return null;
	}

}