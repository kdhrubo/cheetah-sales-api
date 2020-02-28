package com.cheetahapps.sales.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.cheetahapps.sales.domain.Lead;

@RepositoryRestResource(collectionResourceRel = "leads", path = "leads")
public interface LeadRepository extends MongoRepository<Lead, String> {
	
	List<Lead> findAllByDeleted(Pageable pageable, boolean deleted);
}
