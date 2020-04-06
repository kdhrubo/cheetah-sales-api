package com.cheetahapps.sales.business;

import java.util.Map;
import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.cheetahapps.sales.domain.Form;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FormBusinessDelegate {
	@Value("classpath*:form-*.json")
	private Resource [] formResources;
	
	private Map<String, Form> forms = new HashMap<>();
	
	private final ObjectMapper objectMapper;
	
	
	public Form findByName(String name) {
		return forms.get(name);
	}
	
	//TODO Add to provision as well when starting to save this in DB
	@PostConstruct
	public void init() throws Exception{
		log.info("== loading forms ===");
		for(Resource r : formResources) {
			log.info("@@@@ Loading form - {}", r.getFilename());
			
			Form form = objectMapper.readValue(r.getInputStream(), Form.class);
			
			log.info("@@@@ The form - {}", form);
			
			forms.put(form.getName(), form);
		}
	}
	
}
