package com.cheetahapps.sales.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cheetahapps.sales.domain.User;
import com.cheetahapps.sales.dto.UserView;
import com.cheetahapps.sales.repository.UserRepository;

import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	

	public Option<UserView> getById(String id) {
		return this.userRepository.getById(id);
	}

	

	

}
