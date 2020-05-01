package com.cheetahapps.sales.deal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.cheetahapps.sales.core.AbstractBaseController;
import com.cheetahapps.sales.lead.Lead;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/deals")

public class DealController extends AbstractBaseController<Deal, String> {

	DealBusinessDelegate dealBusinessDelegate;
	
	@Autowired
	public DealController(DealBusinessDelegate dealBusinessDelegate) {
		super(dealBusinessDelegate);
		this.dealBusinessDelegate = dealBusinessDelegate;
	}
	
	@GetMapping("/search")
	public Page<Deal> search(@RequestParam("rsql") String rsql, @PageableDefault Pageable pageable) {
		log.info("Deal business delegate - {}", this.businessDelegate);
		log.info("rsql - {}", rsql);
		log.info("pageable - {}", pageable);
		return dealBusinessDelegate.search(rsql, pageable);
	}

}