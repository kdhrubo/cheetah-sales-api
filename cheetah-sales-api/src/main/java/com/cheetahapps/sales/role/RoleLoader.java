package com.cheetahapps.sales.role;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.cheetahapps.sales.event.ProvisionTenantEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class RoleLoader  {

	@Value("classpath:/data/roles.csv")
	private Resource resource;

	private final RoleBusinessDelegate roleBusinessDelegate;

	@EventListener
	public void provision(ProvisionTenantEvent event) throws IOException {
		if (!event.isExistingTenant()) {
			load();
		}
	}

	public void load() throws IOException {

		try (Stream<String> stream = Files.lines(Paths.get(resource.getURI()))) {
			stream.forEach(i -> {

				roleBusinessDelegate.findByName(i).onEmpty(() -> {
					log.info("Creating system role - {}", i);
					Role role = Role.builder().name(i).system(true).build();

					this.roleBusinessDelegate.save(role);
				});

			});
		}
	}

}
