package com.cheetahapps.sales.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SearchRepositoryCustomImpl<T> implements SearchRepositoryCustom<T>{
	
	private final MongoTemplate mongotTemplate;

	@Override
	public Page<T> search(Criteria criteria, Pageable pageable, Class<T> clazz) {
		Query query = new Query(criteria).with(pageable);
		List<T> filteredResults = mongotTemplate.find(query, clazz);
		
		return PageableExecutionUtils.getPage(
				filteredResults,
		        pageable,
		        () -> mongotTemplate.count(query, clazz));
	}

}
