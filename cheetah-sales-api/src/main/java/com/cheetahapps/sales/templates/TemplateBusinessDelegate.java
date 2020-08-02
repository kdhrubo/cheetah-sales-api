package com.cheetahapps.sales.templates;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.event.ProvisionTenantEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.vavr.control.Option;
import io.vavr.control.Try;
import jodd.template.MapTemplateParser;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TemplateBusinessDelegate extends AbstractBusinessDelegate<Template, String> {

	@Value("classpath:email/*.html")
	private Resource[] formResources;

	private TemplateRepository templateRepository;

	@Autowired
	private ObjectMapper objectMapper;

	public TemplateBusinessDelegate(TemplateRepository repository) {
		super(repository);
		this.templateRepository = repository;
	}
	
	@Transactional
	@EventListener
	public void provision(ProvisionTenantEvent event) {
		
		if (!event.isExistingTenant()) {
			for (Resource r : formResources) {
				

				String templateText = Try.of(() -> readTemplate(r)).getOrNull();

				if (StringUtil.isNotEmpty(templateText)) {
					String name = StringUtil.remove(r.getFilename(), ".html");
					Template mailTemplate = Template.builder().name(name).templateText(templateText).build();
					this.templateRepository.save(mailTemplate);
				} else {
					log.info("Invalid template text. Rejecting file - {}", r.getFilename());
				}

			}
		}
	}

	private String readTemplate(Resource resource) throws Exception {
		byte[] bdata = FileCopyUtils.copyToByteArray(resource.getInputStream());
		return new String(bdata, StandardCharsets.UTF_8);
	}

	public String getMergedTemplate(String templateName, Object object) throws Exception {
		
		Option<Template> template = templateRepository.findByName(templateName);

		if (template.isEmpty()) {
			
			return null;
		} else {
			
			MapTemplateParser parser = new MapTemplateParser();
			Map<String, Object> data = objectMapper.convertValue(object, Map.class);
			
			String s = parser.parseWithMap(template.get().getTemplateText(), data);
			
			return s;
		}

	}

}
