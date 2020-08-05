package com.cheetahapps.sales.core;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;

public interface SearchRepositoryCustom<T> {
	
	Page<T> search(String rsql, Pageable pageable, Class<T> clazz);
	
	List<T> searchAll(String rsql, Class<T> clazz);
	
}
