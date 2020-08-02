package com.cheetahapps.sales.jobs.provisoning;

import java.util.Map;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.cheetahapps.sales.event.SendEmailEvent;

import com.cheetahapps.sales.user.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendRegistrationConfirmationEmailTasklet implements Tasklet {
	
	private final ApplicationEventPublisher publisher;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		Map<String,Object> jobExecutionContext = chunkContext.getStepContext().getJobExecutionContext();
		
		User user = (User)jobExecutionContext.get("user");
		
		log.info("User - {}", user);
		
		SendEmailEvent<User> event = SendEmailEvent.of("system_mail",
				"support@cheetahdesk.com", "Nirvaana Support", user.getEmail(), "Welcome to Nirvaana", 
				"welcome", user);
		
		publisher.publishEvent(event);
		
		
		return RepeatStatus.FINISHED;
	}

}
