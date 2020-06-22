package com.cheetahapps.sales.jobs.provisoning;


import java.util.Map;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import com.cheetahapps.sales.event.ProvisionTenantEvent;
import com.cheetahapps.sales.integration.mail.MailSetting;
import com.cheetahapps.sales.tenant.Tenant;
import com.cheetahapps.sales.user.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class PrepareTenantProvisioningTasklet implements Tasklet{
	
	private final MailSetting mailSetting;
	private final ApplicationEventPublisher publisher;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		log.info("Preparing to start provisioning activities for tenant.");
		
		Map<String,Object> jobExecutionContext = chunkContext.getStepContext().getJobExecutionContext();
		Tenant tenant = (Tenant)jobExecutionContext.get("tenant");
		User user = (User)jobExecutionContext.get("user");
		boolean existingTenant = (boolean)jobExecutionContext.get("existingTenant");
	
		
		
		
		JobRequestAttributes attributes = new JobRequestAttributes();
		attributes.setAttribute("tenantCode", tenant.getCode(), JobRequestAttributes.SCOPE_REQUEST);

		RequestContextHolder.setRequestAttributes(attributes);
		
		
		publisher.publishEvent(ProvisionTenantEvent
				.of(user, tenant, existingTenant, mailSetting.to("system_mail","mail", "nirvaana-dreamhost")));
		
	
		return RepeatStatus.FINISHED;
	}
	
	
}
