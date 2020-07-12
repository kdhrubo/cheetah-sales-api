package com.cheetahapps.sales.core;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;

public interface SearchRepositoryCustom<T> {
	
	Page<T> search(String rsql, Pageable pageable, Class<T> clazz);
	
	@Deprecated
	Page<T> search(Criteria criteria, Pageable pageable, Class<T> clazz);
	
	@Deprecated
	List<T> searchAll(Criteria criteria, Class<T> clazz);
}
