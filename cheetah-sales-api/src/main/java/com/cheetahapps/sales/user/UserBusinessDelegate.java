package com.cheetahapps.sales.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.role.Role;

import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserBusinessDelegate extends AbstractBusinessDelegate<User, String>{
	
	private UserRepository userRepository;
	
	public UserBusinessDelegate(UserRepository userRepository) {
		super(userRepository);
		this.userRepository = userRepository;
	}
	
	public void provision(User user) {
		log.info("Provisioning new user.");
		save(user);
	}
	
	@Transactional(readOnly = true)
	public Option<User> findByEmail(String email) {
		log.info("Finding user with email - {}", email);
		return userRepository.findByEmail(email);
	}
	
	@Transactional
	public User create(String firstName, String lastName, String email, String password, String tenantId, 
			String tenantCode, String tenantName, Role role) {
		Option<User> usr = findByEmail(email);
		User user = null;
		if(usr.isEmpty()) {
			//create new user with given role
			
			if(role.isAdmin()) {
				user = User.builder().email(email).firstName(firstName).lastName(lastName)
						.role(role).tenantId(tenantId).tenantCode(tenantCode).tenantName(tenantName)
						.build();
			}
			else {
				user = User.builder().deleted(true).email(email).firstName(firstName).lastName(lastName)
						.role(role).tenantId(tenantId).tenantCode(tenantCode).tenantName(tenantName)
						.build();
			}
		}
		else {
			//error - user exists
		}
		
		
		return user;
	}
	
	
	
}
