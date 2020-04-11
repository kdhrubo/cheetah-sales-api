package com.cheetahapps.sales.note;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import com.cheetahapps.sales.business.AbstractBaseBusinessDelegate;
import com.github.rutledgepaulv.qbuilders.builders.GeneralQueryBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.visitors.MongoVisitor;
import com.github.rutledgepaulv.rqe.pipes.QueryConversionPipeline;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NoteBusinessDelegate extends AbstractBaseBusinessDelegate<Note, String> {

	private final NoteRepository repository;

	private QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();

	public NoteBusinessDelegate(NoteRepository repository) {
		super(repository);
		this.repository = repository;
	}

	public List<Note> searchAll(String rsql) {
		// "firstName==Paul;age==30"
		// "deleted==false"
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, Note.class);
		Criteria criteria = condition.query(new MongoVisitor());

		return repository.searchAll(criteria, Note.class);
	}

}