package com.cheetahapps.sales.phone;

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
public class PhoneBusinessDelegate extends AbstractBaseBusinessDelegate<Phone, String> {

	private PhoneRepository phoneRepository;

	private QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();

	public PhoneBusinessDelegate(PhoneRepository repository) {
		super(repository);
		this.phoneRepository = repository;
	}

	public List<Phone> searchAll(String rsql) {
		// "firstName==Paul;age==30"
		// "deleted==false"
		log.debug("Searching Phone - {}", rsql);
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, Phone.class);
		Criteria criteria = condition.query(new MongoVisitor());

		return phoneRepository.searchAll(criteria, Phone.class);
	}

}
