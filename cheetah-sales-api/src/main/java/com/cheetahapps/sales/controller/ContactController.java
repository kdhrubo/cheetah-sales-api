package com.cheetahapps.sales.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.cheetahapps.sales.business.ContactBusinessDelegate;
import com.cheetahapps.sales.domain.Contact;

@Controller
@RequestMapping("/contacts")

public class ContactController extends AbstractBaseController<Contact, String> {
	
	private ContactBusinessDelegate businessDelegate;

	@Autowired
	public ContactController(ContactBusinessDelegate businessDelegate) {
		super(businessDelegate);
		this.businessDelegate = businessDelegate;
	}

	@RequestMapping("/find")
	@ResponseBody
	public List<Contact> findByFirstNameLike(@RequestParam(name = "firstName") String firstName) {
		return businessDelegate.findByFirstNameLike("%" + firstName + "%");
	}

}