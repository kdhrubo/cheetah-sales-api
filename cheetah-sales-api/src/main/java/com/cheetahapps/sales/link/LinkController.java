package com.cheetahapps.sales.link;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cheetahapps.sales.core.AbstractBaseController;

@RestController
@RequestMapping("/links")
public class LinkController extends AbstractBaseController<Link, String> {

	private final LinkBusinessDelegate linkBusinessDelegate;

	public LinkController(LinkBusinessDelegate linkBusinessDelegate) {
		super(linkBusinessDelegate);
		this.linkBusinessDelegate = linkBusinessDelegate;
	}

	@GetMapping("/q")
	public List<Link> searchAll(@RequestParam("rsql") String rsql) {
		return linkBusinessDelegate.searchAll(rsql);
	}
}