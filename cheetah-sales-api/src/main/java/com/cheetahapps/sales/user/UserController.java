package com.cheetahapps.sales.user;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
	
	
	@PostMapping("/register")
	public UserView register(@RequestBody UserDto userDto) {
		log.info("Registering user");

		/*
		User user = User.builder().email(userDto.getEmail()).firstName(userDto.getFirstName())
				.lastName(userDto.getLastName()).password(userDto.getPassword()).tenantName(userDto.getCompany()).build();
		
		User u = this.userBusinessDelegate.register(user, userDto.getCompany(), userDto.getCountry());
		
		return new UserView(u.getId(), u.getFirstName(), u.getLastName(), u.getEmail(), u.getCreatedDate(), null);
		*/
		
		return null;
	}

	
}
