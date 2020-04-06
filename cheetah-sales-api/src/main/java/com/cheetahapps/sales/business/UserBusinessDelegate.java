package com.cheetahapps.sales.business;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.cheetahapps.sales.domain.User;
import com.cheetahapps.sales.event.ProvisionTenantEvent;
import com.cheetahapps.sales.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserBusinessDelegate extends AbstractBaseBusinessDelegate<User, String>{
	
	private UserRepository userRepository;
	
	public UserBusinessDelegate(UserRepository userRepository) {
		super(userRepository);
		this.userRepository = userRepository;
	}
	
	@EventListener
	public void provision(ProvisionTenantEvent event) {
		log.info("== Provisioning new users. ===");
		userRepository.save(event.getUser());
	}

}
