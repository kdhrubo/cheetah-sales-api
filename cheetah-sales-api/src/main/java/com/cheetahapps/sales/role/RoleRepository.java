package com.cheetahapps.sales.role;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.vavr.control.Option;


interface RoleRepository extends MongoRepository<Role, String> {

	Option<Role> findByName(String name);
}
