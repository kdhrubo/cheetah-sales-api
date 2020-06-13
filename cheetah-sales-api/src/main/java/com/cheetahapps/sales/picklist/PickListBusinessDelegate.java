package com.cheetahapps.sales.picklist;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PickListBusinessDelegate extends AbstractBusinessDelegate<PickList, String> {

	private PickListRepository repository;

	@Value("classpath:/data/picklist.csv")
	private Resource resource;

	public PickListBusinessDelegate(PickListRepository repository) {
		super(repository);
		this.repository = repository;
	}

	@Transactional(readOnly = true)
	public List<PickList> findByDomain(String domain) {

		log.debug("Getting domain values - " + domain);

		return repository.findByDomain(domain);
	}

	@Transactional(readOnly = true)
	public List<PickList> findByDomain(String domain, boolean required) {

		return repository.findByDomain(domain);
	}

	@Transactional
	public void provision() {
		log.info("Provisioning picklist -- {}", resource);

		// reading csv file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(resource.getURI()))) {

			stream.map(i -> {

				String s[] = i.split(",");
				return PickList.builder().domain(s[0]).value(s[1]).build();
			}).forEach(i -> {

				this.repository.findByDomainAndValue(i.getDomain(), i.getValue())
						.onEmpty(() -> this.repository.save(i));

			});

		} catch (Exception e) {
			log.error("Error loading picklist file - {}", e);
		}

	}

}