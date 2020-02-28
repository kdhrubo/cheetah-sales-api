package com.cheetahapps.sales.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cheetahapps.sales.domain.Role;




@Repository
public interface RoleRepository extends MongoRepository<Role, Integer> {

	Role findByName(String name);
}
