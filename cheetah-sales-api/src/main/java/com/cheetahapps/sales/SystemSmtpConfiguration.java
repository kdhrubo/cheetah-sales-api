package com.cheetahapps.sales;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jodd.mail.MailServer;
import jodd.mail.SmtpServer;

@Configuration
public class SystemSmtpConfiguration {
	@Value("${smtp.username}")
	private String smtpUser;
	@Value("${smtp.password}")
	private String smtpPassword;
	@Value("${smtp.server}")
	private String smtpServer;
	@Value("${smtp.port}")
	private int smtpPort;
	@Value("${smtp.useTls}")
	private boolean allowTls;
	
	@Bean
	public SmtpServer smtpServer() {
		SmtpServer server = MailServer.create()
	            .ssl(allowTls).host(smtpServer)
	            .port(smtpPort)
	            .auth(smtpUser, smtpPassword)
	            .buildSmtpMailServer();
		
		return server;
	}
}
