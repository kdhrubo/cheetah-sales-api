package com.cheetahapps.sales.controller;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheetahapps.sales.domain.User;
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
	
	
	@PostMapping("/register")
	public UserView register(@RequestBody User user) {
		User u = this.userService.register(user);
		return new UserView(u.getId(), u.getFullName(), u.getEmail(), u.getCreatedDate(), null);
	}

	@GetMapping("/whoami")
	public UserView whoami(@AuthenticationPrincipal Jwt jwt) {

		log.info("jwt - {}", jwt.getClaims());
		
		String id = (String
				) jwt.getClaims().get("user_id");
		
		
		

		return userService.getById(id).get();
	}
	
	@PostMapping("/otp/{email}")
	public UserView generateVerificationCode(@PathVariable String email) throws Exception{
		User u = userService.generateOtp(email);
		return new UserView(u.getId(), u.getFullName(), u.getEmail(), u.getCreatedDate(), null);
	}
	
	@PostMapping("/changepassword") 
	public UserView updatePassword(@Valid @RequestBody User user) {
		User u = userService.updatePassword(user.getEmail(), user.getVerificationCode(), user.getPassword());
		
		return new UserView(u.getId(), u.getFullName(), u.getEmail(), u.getCreatedDate(), null);
		
	}
}
