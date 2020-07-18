package com.cheetahapps.sales.role;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheetahapps.sales.problem.NoDataFoundProblem;

import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleBusinessDelegate {
	
	private final RoleRepository roleRepository;
	
	@Transactional(readOnly = true)
	public Option<Role> findByName(String name){
		return roleRepository.findByName(name);
	}
	@Transactional
	public Role save(Role role) {
		return this.roleRepository.save(role);
	}
	
	@Transactional
	public List<Role> saveAll(List<Role> roles) {
		return this.roleRepository.saveAll(roles);
	}
	
	@Transactional(readOnly = true)
	public Role getTenantAdmin() {
		return findByName("TENANT_ADMIN")
				.getOrElseThrow(() -> new NoDataFoundProblem("Role not found."));
	}
	
	@Transactional(readOnly = true)
	public Role getTeamMember() {
		return findByName("TEAM_MEMBER")
				.getOrElseThrow(() -> new NoDataFoundProblem("Role not found."));
	}
}
