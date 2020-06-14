package com.cheetahapps.sales.integration.mail;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.cheetahapps.sales.event.SendEmailEvent;
import com.cheetahapps.sales.setting.Setting;
import com.cheetahapps.sales.setting.SettingBusinessDelegate;
import com.cheetahapps.sales.templates.TemplateBusinessDelegate;

import jodd.mail.Email;
import jodd.mail.MailServer;
import jodd.mail.SendMailSession;
import jodd.mail.SmtpServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Component
@Slf4j
public class MailSender {
	
	private final TemplateBusinessDelegate templateBusinessDelegate;
	private final SettingBusinessDelegate settingBusinessDelegate;
	
	@Async
	@EventListener
	public <T> void send(SendEmailEvent<T> event) throws Exception{
		
		log.info("Trying to send email via System SMTP");
		String body = templateBusinessDelegate.getMergedTemplate(event.getTemplateName(), event.getT());
		
		log.info("Body - {}", body);
		Email email = Email.create()
				.from(event.getName(), event.getFrom())
				.to(event.getTo())
				.replyTo(event.getTo())
				.subject(event.getSubject())
				.htmlMessage(body);
		
		Setting setting = settingBusinessDelegate.findByName(event.getSettingName());
		
        try {
        log.info("Going ....");
        
		sendMail(MailSetting.from(setting), email);
        
		log.info("Gone ....");
		
        }
        catch(Exception e) {
        	log.error("Error sending email - ", e);
        }
	}
	
	private void sendMail(MailSetting mailSetting, Email email) {
		SendMailSession session = smtpServer(mailSetting).createSession();
	    session.open();
	    session.sendMail(email);
	    session.close();
	}
	
	private  SmtpServer smtpServer(MailSetting mailSetting) {
		return MailServer.create()
	            .ssl(mailSetting.isAllowTls()).host(mailSetting.getServer())
	            .port(mailSetting.getPort())
	            .auth(mailSetting.getUser(), mailSetting.getPassword())
	            .buildSmtpMailServer();
		
		
	}
	

}


