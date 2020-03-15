package com.cheetahapps.sales.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheetahapps.sales.dto.UserView;
import com.cheetahapps.sales.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
@RestController
public class UserController {
	
	private final UserService userService;

	@GetMapping("/whoami")
	public UserView whoami(@AuthenticationPrincipal Jwt jwt) {

		log.info("jwt - {}", jwt.getClaims());
		
		String id = (String
				) jwt.getClaims().get("user_id");
		
		
		

		return userService.getById(id).get();
	}
	
}
