package com.cheetahapps.sales.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SearchRepositoryCustomImpl<T> implements SearchRepositoryCustom<T>{
	
	private final MongoTemplate mongoTemplate;

	@Override
	public Page<T> search(Criteria criteria, Pageable pageable, Class<T> clazz) {
		Query query = new Query(criteria).with(pageable);
		List<T> filteredResults = mongoTemplate.find(query, clazz);
		
		
		return PageableExecutionUtils.getPage(
				filteredResults,
		        pageable,
		        () -> this.getCountByQuery(criteria, clazz));
	}
	
	@Override
	public List<T> searchAll(Criteria criteria, Class<T> clazz) {
		Query query = new Query(criteria);
		return mongoTemplate.find(query, clazz);
	}
	
	@SuppressWarnings({ "unused", "rawtypes" })
	private long getCountByQuery(Criteria criteria , Class<T> clazz) {
		Aggregation agg = Aggregation.newAggregation(
				Aggregation.match(criteria),
				Aggregation.count().as("recordCount")
             );
		
		
		
		AggregationResults results = 
              mongoTemplate.aggregate(
                      agg ,
                      clazz.getSimpleName(),
                      clazz
              );
		
		//Long.parseLong(results.getMappedResults().get(0).get("count").toString());
		Document document = results.getRawResults();
		
		
		
		int finalCount = ((Document)((List)document.get("results")).get(0))
				.getInteger("recordCount", 0) ;
		
		log.debug("Final count = {}", finalCount);
		
		
		return finalCount;
	}

	

}
