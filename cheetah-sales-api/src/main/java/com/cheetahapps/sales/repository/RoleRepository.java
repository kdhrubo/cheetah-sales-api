package com.cheetahapps.sales.repository;

import org.springframework.stereotype.Repository;

import com.cheetahapps.sales.domain.Role;


@Repository
public interface RoleRepository extends BaseMongoRepository<Role, Integer> {

	Role findByName(String name);
}
