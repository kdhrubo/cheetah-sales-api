package com.cheetahapps.sales.core;

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

import com.cheetahapps.sales.lead.Lead;
import com.github.rutledgepaulv.qbuilders.builders.GeneralQueryBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.visitors.MongoVisitor;
import com.github.rutledgepaulv.rqe.pipes.QueryConversionPipeline;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SearchRepositoryCustomImpl<T> implements SearchRepositoryCustom<T>{
	
	private QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();
	
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
		
		
		Document document = results.getRawResults();
		
		
		
		int finalCount = ((Document)((List)document.get("results")).get(0))
				.getInteger("recordCount", 0) ;
		
		log.debug("Final count = {}", finalCount);
		
		
		return finalCount;
	}

	@Override
	public Page<T> search(String rsql, Pageable pageable, Class<T> clazz) {
		
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, clazz);
		Criteria criteria = condition.query(new MongoVisitor());
		return search(criteria, pageable, clazz);
	}

	@Override
	public List<T> searchAll(String rsql, Class<T> clazz) {
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, clazz);
		Criteria criteria = condition.query(new MongoVisitor());
		return searchAll(criteria, clazz);
	}

	

}
