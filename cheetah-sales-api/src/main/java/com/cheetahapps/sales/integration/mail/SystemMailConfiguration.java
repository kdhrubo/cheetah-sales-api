package com.cheetahapps.sales.integration.mail;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SystemMailConfiguration {
	
	
	@Bean
    @ConfigurationProperties(prefix = "smtp")
	public MailSetting systemMailSetting() {
		return new MailSetting();
	}
}
