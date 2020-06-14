package com.cheetahapps.sales.templates;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
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

	public void provision() {
		for (Resource r : formResources) {
			log.info("File - {}", r.getFilename());

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

	private String readTemplate(Resource resource) throws Exception {
		byte[] bdata = FileCopyUtils.copyToByteArray(resource.getInputStream());
		return new String(bdata, StandardCharsets.UTF_8);
	}

	public String getMergedTemplate(String templateName, Object object) throws Exception {

		Option<Template> template = templateRepository.findByName(templateName);

		if (template.isEmpty()) {
			// throw error
			return null;
		} else {
			MapTemplateParser parser = new MapTemplateParser();
			Map<String,Object> data = objectMapper.convertValue(object, Map.class);
			
			return parser.parseWithMap(template.get().getTemplateText(), data);
		}

	}

}
