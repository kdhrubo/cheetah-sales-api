package com.cheetahapps.sales.repository;

import org.springframework.stereotype.Repository;

import com.cheetahapps.sales.domain.User;
import com.cheetahapps.sales.dto.UserView;

import io.vavr.control.Option;

@Repository
public interface UserRepository extends BaseMongoRepository<User, String> {

	Option<User> findByEmail(String email);

	Option<UserView> getById(String id);
}
