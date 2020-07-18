package com.cheetahapps.sales.role;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.cheetahapps.sales.event.ProvisionTenantEvent;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class RoleLoader {

	@Value("classpath:/data/roles.csv")
	private Resource resource;

	private final RoleBusinessDelegate roleBusinessDelegate;

	@EventListener
	public void provision(ProvisionTenantEvent event) {
		if (!event.isExistingTenant()) {
			load();
		}
	}

	public void load() {

		Try.run(() ->

		roleBusinessDelegate.saveAll(

				Files.lines(Paths.get(resource.getURI())).map(i -> Role.builder().name(i).system(true).build())
						.collect(Collectors.toList())

		)).onFailure(e -> log.info("Failed to provision roles . - {}", e.getMessage()))
		.onSuccess(t -> log.info("Roles provisioned successfully."));

	}

}
