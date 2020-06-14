package com.cheetahapps.sales.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.role.Role;

import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserBusinessDelegate extends AbstractBusinessDelegate<User, String>{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
		String encryptedPwd = this.passwordEncoder.encode(password);
		if(usr.isEmpty()) {
			//create new user with given role
			
			log.info("Creating new user.");
			
			if(role.isAdmin()) {
				log.info("admin level.");
				user = User.builder().email(email).firstName(firstName).lastName(lastName).password(encryptedPwd)
						.role(role).tenantId(tenantId).tenantCode(tenantCode).tenantName(tenantName)
						.build();
				
			}
			else {
				log.info("team member level.");
				user = User.builder().deleted(true).email(email).firstName(firstName).lastName(lastName).password(encryptedPwd)
						.role(role).tenantId(tenantId).tenantCode(tenantCode).tenantName(tenantName)
						.build();
			}
		}
		else {
			//error - user exists
			log.info("User already exists - ERROR!!!!");
		}
		
		
		return save(user);
	}
	
	
	
}
