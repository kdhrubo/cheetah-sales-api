package com.cheetahapps.sales.picklist;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cheetahapps.sales.core.AbstractBaseController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/picklists")
@Slf4j
public class PickListController extends AbstractBaseController<PickList, String> {

	private PickListBusinessDelegate pickListBusinessDelegate;

	public PickListController(PickListBusinessDelegate pickListBusinessDelegate) {
		super(pickListBusinessDelegate);
		this.pickListBusinessDelegate = pickListBusinessDelegate;
	}

	@GetMapping("/{domain}")
	public List<PickList> findByDomain(@PathVariable String domain) {

		log.info("Getting domain values - {}" , domain);

		return pickListBusinessDelegate.findByDomain(domain);
	}
}