package com.cheetahapps.sales.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.cheetahapps.sales.domain.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

	Role findByName(String name);
}
