package com.cheetahapps.sales.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.event.ProvisionTenantEvent;
import com.cheetahapps.sales.role.Role;
import com.cheetahapps.sales.role.RoleBusinessDelegate;
import com.cheetahapps.sales.tenant.Tenant;
import com.cheetahapps.sales.tenant.TenantBusinessDelegate;

import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserBusinessDelegate extends AbstractBusinessDelegate<User, String> {

	@Autowired
	private PasswordEncoder passwordEncoder;
	private UserRepository userRepository;
	
	
	//service to service integration
	@Autowired
	private TenantBusinessDelegate tenantBusinessDelegate;
	@Autowired
	private RoleBusinessDelegate roleBusinessDelegate;

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
	
	@Transactional
	public User add(UserDto userDto) {
		
		
		Optional<Tenant> t = this.tenantBusinessDelegate.findById(this.getAuthUser().getTenantId());
		
		// it exists no need for checking
		String encryptedPwd = this.passwordEncoder.encode(userDto.getPassword());
		Role role = roleBusinessDelegate.getTeamMember();
		
		userDto.setRole(role);

		User user = User.builder().email(userDto.getEmail()).firstName(userDto.getFirstName())
				.lastName(userDto.getLastName()).deleted(userDto.isDeleted()).password(encryptedPwd)
				.role(role).tenantId(t.get().getId()).tenantCode(t.get().getCode())
				.tenantName(t.get().getName()).build();

		return save(user);
		
	}
	
	@Transactional
	public Page<User> search(String rsql, Pageable pageable) {
		return userRepository.search(rsql, pageable, User.class);
	}

}
