package com.cheetahapps.sales.emailaddress;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class EmailAddressBusinessDelegate extends AbstractBaseBusinessDelegate<EmailAddress, String> {

	private EmailAddressRepository emailAddressRepository;

	private QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();

	public EmailAddressBusinessDelegate(EmailAddressRepository repository) {
		super(repository);
		this.emailAddressRepository = repository;
	}

	public List<EmailAddress> searchAll(String rsql) {
		// "firstName==Paul;age==30"
		// "deleted==false"
		log.debug("Searching EmailAddress - {}", rsql);
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, EmailAddress.class);
		Criteria criteria = condition.query(new MongoVisitor());

		return emailAddressRepository.searchAll(criteria, EmailAddress.class);
	}

}
