package com.cheetahapps.sales.form;

import java.util.Map;
import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.cheetahapps.sales.event.ProvisionTenantEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FormBusinessDelegate {
	@Value("classpath*:form-*.json")
	private Resource[] formResources;

	private Map<String, Form> forms = new HashMap<>();

	private final ObjectMapper objectMapper;

	public Form findByName(String name) {
		return forms.get(name);
	}

	// Remove when the forms settle down
	@PostConstruct
	public void init() throws Exception {

		load();
	}
	
	@EventListener
	public void provision(ProvisionTenantEvent event) throws Exception {
		if (!event.isExistingTenant()) {
			load();
		}
	}

	public void load() throws Exception {

		for (Resource r : formResources) {

			Form form = objectMapper.readValue(r.getInputStream(), Form.class);

			forms.put(form.getName(), form);
		}

	}

}
