package com.cheetahapps.sales.deal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.cheetahapps.sales.core.AbstractController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/deals")

public class DealController extends AbstractController<Deal, String> {

	DealBusinessDelegate dealBusinessDelegate;
	
	@Autowired
	public DealController(DealBusinessDelegate dealBusinessDelegate) {
		super(dealBusinessDelegate);
		this.dealBusinessDelegate = dealBusinessDelegate;
	}
	
	@GetMapping("/search")
	public Page<Deal> search(@RequestParam("rsql") String rsql, @PageableDefault Pageable pageable) {
		return dealBusinessDelegate.search(rsql, pageable);
	}

}