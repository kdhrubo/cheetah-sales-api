package com.effectiv.crm.repository.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cheetahapps.sales.domain.Contact;
import com.cheetahapps.sales.repository.BaseMongoRepository;


@Repository
public interface ContactRepository extends BaseMongoRepository<Contact, String> {
	List<Contact> findByFirstNameLike(String firstName);
}
