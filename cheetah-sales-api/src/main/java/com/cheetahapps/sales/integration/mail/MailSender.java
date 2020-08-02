package com.cheetahapps.sales.integration.mail;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.cheetahapps.sales.event.SendEmailEvent;
import com.cheetahapps.sales.setting.Setting;
import com.cheetahapps.sales.setting.SettingBusinessDelegate;
import com.cheetahapps.sales.templates.TemplateBusinessDelegate;

import io.vavr.control.Try;
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

	//@Async
	@EventListener
	public <T> void send(SendEmailEvent<T> event) {

		log.info("Trying to send email.");

		Try.run(() -> {

			String body = templateBusinessDelegate.getMergedTemplate(event.getTemplateName(), event.getT());


			Email email = Email.create().from(event.getName(), event.getFrom()).to(event.getTo()).replyTo(event.getTo())
					.subject(event.getSubject()).htmlMessage(body);

			Setting setting = settingBusinessDelegate.findByName(event.getSettingName());
			
			log.info("setting - {}" , setting);

			MailSetting mailSetting = new MailSetting();

			sendMail(mailSetting.from(setting), email);

		});

	}

	private void sendMail(MailSetting mailSetting, Email email) {
		log.info("cONNECTING TO EMAIL SERVER - {}", mailSetting);
		SendMailSession session = smtpServer(mailSetting).createSession();
		
		session.open();
		try {
		String s = session.sendMail(email);
		log.info("send email - {}", s);
		session.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private SmtpServer smtpServer(MailSetting mailSetting) {
		return MailServer.create().ssl(mailSetting.isAllowTls()).host(mailSetting.getServer())
				.port(mailSetting.getPort()).auth(mailSetting.getUser(), mailSetting.getPassword())
				.buildSmtpMailServer();

	}

}
