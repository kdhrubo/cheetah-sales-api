package com.cheetahapps.sales.link;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import com.cheetahapps.sales.core.AbstractBaseBusinessDelegate;
import com.github.rutledgepaulv.qbuilders.builders.GeneralQueryBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.visitors.MongoVisitor;
import com.github.rutledgepaulv.rqe.pipes.QueryConversionPipeline;


@Component
@Slf4j
public class LinkBusinessDelegate extends AbstractBaseBusinessDelegate<Link, String> {

	private LinkRepository linkRepository;

	private QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();

	public LinkBusinessDelegate(LinkRepository repository) {
		super(repository);
		this.linkRepository = repository;
	}

	public List<Link> searchAll(String rsql) {
		// "firstName==Paul;age==30"
		// "deleted==false"
		log.debug("Searching EmailAddress - {}", rsql);
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, Link.class);
		Criteria criteria = condition.query(new MongoVisitor());

		return linkRepository.searchAll(criteria, Link.class);
	}

}
