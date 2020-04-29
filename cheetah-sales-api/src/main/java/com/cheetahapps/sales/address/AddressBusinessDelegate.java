package com.cheetahapps.sales.address;

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
public class AddressBusinessDelegate extends AbstractBaseBusinessDelegate<Address, String> {

	private AddressRepository addressRepository;

	private QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();

	public AddressBusinessDelegate(AddressRepository repository) {
		super(repository);
		this.addressRepository = repository;
	}

	public List<Address> searchAll(String rsql) {
		// "firstName==Paul;age==30"
		// "deleted==false"
		log.debug("Searching EmailAddress - {}", rsql);
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, Address.class);
		Criteria criteria = condition.query(new MongoVisitor());

		return addressRepository.searchAll(criteria, Address.class);
	}

}
