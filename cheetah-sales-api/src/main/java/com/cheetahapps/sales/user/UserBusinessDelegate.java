package com.cheetahapps.sales.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.event.ProvisionTenantEvent;

import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserBusinessDelegate extends AbstractBusinessDelegate<User, String> {

	@Autowired
	private PasswordEncoder passwordEncoder;
	private UserRepository userRepository;

	public UserBusinessDelegate(UserRepository userRepository) {
		super(userRepository);
		this.userRepository = userRepository;
	}
	
	@Transactional
	@EventListener
	public void provision(ProvisionTenantEvent event) {
		log.info("Provisioning new user.");
		save(event.getUser());
	}

	@Transactional(readOnly = true)
	public Option<User> findByEmail(String email) {
		log.info("Finding user with email - {}", email);
		return userRepository.findByEmail(email);
	}

	@Transactional
	public User create(UserDto userDto) {

		String encryptedPwd = this.passwordEncoder.encode(userDto.getPassword());

		User user = User.builder().email(userDto.getEmail()).firstName(userDto.getFirstName())
				.lastName(userDto.getLastName()).deleted(userDto.isDeleted()).password(encryptedPwd)
				.role(userDto.getRole()).tenantId(userDto.getTenantId()).tenantCode(userDto.getTenantCode())
				.tenantName(userDto.getTenantName()).build();

		return save(user);
	}

}
