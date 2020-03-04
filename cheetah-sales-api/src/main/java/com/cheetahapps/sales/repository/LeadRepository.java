package com.cheetahapps.sales.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.cheetahapps.sales.domain.Lead;

@Repository
public interface LeadRepository extends BaseMongoRepository<Lead, String> {
	
	List<Lead> findAllByDeleted(Pageable pageable, boolean deleted);
}
