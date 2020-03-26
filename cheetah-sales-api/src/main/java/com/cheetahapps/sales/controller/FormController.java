package com.cheetahapps.sales.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheetahapps.sales.business.FormBusinessDelegate;

import com.cheetahapps.sales.domain.FormFieldConfig;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/forms")
@RequiredArgsConstructor
public class FormController {
	
	private final FormBusinessDelegate formBusinessDelegate;
	
	@GetMapping("/{name}")
	public List<FormFieldConfig> findByName(@PathVariable String name) {
		return this.formBusinessDelegate.findByName(name).getFields();
	}
}
