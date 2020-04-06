package com.cheetahapps.sales.controller;

import javax.validation.Valid;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheetahapps.sales.domain.User;
import com.cheetahapps.sales.event.ProvisionTenantEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
	
	private final ApplicationEventPublisher eventPublisher;

	@PostMapping("/provision")
	public void notifyUserRegistration(@Valid @RequestBody User u) {
		
		log.info("=== new user data to be provisioned ==");
		
		eventPublisher.publishEvent(new ProvisionTenantEvent(u, this));
		
	}
}
