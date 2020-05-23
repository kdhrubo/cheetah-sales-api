package com.cheetahapps.sales.user;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.event.ProvisionTenantEvent;

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
	
	@Transactional(readOnly = true)
	public Option<User> findByTenantId(String tenantId) {
		return userRepository.findByTenantId(tenantId);
	}
	
	@EventListener
	public void provision(ProvisionTenantEvent event) {
		log.info("Provisioning new users.");
		userRepository.save(event.getUser());
	}

}
