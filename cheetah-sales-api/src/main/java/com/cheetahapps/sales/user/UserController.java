package com.cheetahapps.sales.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheetahapps.sales.core.AbstractController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController extends AbstractController<User, String> {

	private UserBusinessDelegate userBusinessDelegate;

	public UserController(UserBusinessDelegate businessDelegate) {
		super(businessDelegate);
		this.userBusinessDelegate = businessDelegate;
	}

	@PostMapping("/register")
	public UserDto register(@RequestBody UserDto userDto) {
		log.info("Registering user - {}", userDto);

		User u = userBusinessDelegate.register(userDto);
		userDto.setId(u.getId());
		return userDto;
	}

}
