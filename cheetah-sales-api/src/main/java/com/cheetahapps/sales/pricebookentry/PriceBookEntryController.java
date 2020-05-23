package com.cheetahapps.sales.pricebookentry;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import com.cheetahapps.sales.core.AbstractController;

/**
 * 
 * @author jay
 * 
 */
@RestController
@RequestMapping("/pricebookentry")
@Slf4j
public class PriceBookEntryController extends AbstractController<PriceBookEntry, String>{

	
	private final PriceBookEntryBusinessDelegate priceBookEntryBusinessDelegate;
	
	public PriceBookEntryController(PriceBookEntryBusinessDelegate priceBookBusinessDelegate) {
		super(priceBookBusinessDelegate);
		this.priceBookEntryBusinessDelegate = priceBookBusinessDelegate;
	}

	
	@GetMapping("/q")
	public Page<PriceBookEntry> search(@RequestParam("rsql") String rsql, Pageable pageable) {
		log.debug("Search price book");
		return priceBookEntryBusinessDelegate.search(rsql, pageable);
	}
	
	@PostMapping("/import")
	public List<PriceBookEntry> saveAll(@RequestBody PriceBookEntryList priceBookEntryWrapper) {
		log.info("## In saveAll --> {}", priceBookEntryWrapper);
		
		return this.priceBookEntryBusinessDelegate.saveAll(priceBookEntryWrapper.getPriceBookEntry());
	}
	

}
