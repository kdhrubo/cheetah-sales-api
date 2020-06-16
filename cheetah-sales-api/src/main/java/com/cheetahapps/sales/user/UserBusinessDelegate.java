package com.cheetahapps.sales.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.problem.DuplicateTenantProblem;
import com.cheetahapps.sales.problem.DuplicateUserProblem;
import com.cheetahapps.sales.role.Role;
import com.cheetahapps.sales.role.RoleBusinessDelegate;
import com.cheetahapps.sales.tenant.Tenant;
import com.cheetahapps.sales.tenant.TenantBusinessDelegate;

import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserBusinessDelegate extends AbstractBusinessDelegate<User, String>{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	private UserRepository userRepository;
	
	@Autowired
	private TenantBusinessDelegate tenantBusinessDelegate;
	@Autowired
	private RoleBusinessDelegate roleBusinessDelegate;
	
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
	public User register(UserDto userDto) {
		//check tenant first
		Option<Tenant> tenant = 
				tenantBusinessDelegate.findByName(userDto.getCompany());
	
		if(!tenant.isEmpty()) {
			throw new DuplicateTenantProblem("Please contact company admin to signup.");
		}
				

		Option<User> usr = findByEmail(userDto.getEmail());
		
		if(!usr.isEmpty()) {
			throw new DuplicateUserProblem("User already exists. Forgot password link can be used to recover password.");
		}
		
		//create new tenant
		
		Tenant t = tenantBusinessDelegate.create(userDto.getCompany());
		String encryptedPwd = this.passwordEncoder.encode(userDto.getPassword());
		Role role = roleBusinessDelegate.getTenantAdmin();
		
		return create(userDto, t, encryptedPwd, role);
	}
	
	@Transactional
	public User create(UserDto userDto, Tenant t, String encryptedPwd, Role role) {
		
		User user = User.builder().email(userDto.getEmail())
				.firstName(userDto.getFirstName())
				.lastName(userDto.getLastName())
				.password(encryptedPwd)
				.role(role)
				.tenantId(t.getId())
				.tenantCode(t.getCode())
				.tenantName(t.getName()).build();
		
		return save(user);
	}
	
	
	
}
