package com.cheetahapps.sales.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;

public interface SearchRepositoryCustom<T> {
	
	Page<T> search(Criteria criteria, Pageable pageable, Class<T> clazz);
}
