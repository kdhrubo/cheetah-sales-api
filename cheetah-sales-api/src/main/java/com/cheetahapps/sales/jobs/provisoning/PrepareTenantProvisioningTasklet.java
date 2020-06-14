package com.cheetahapps.sales.jobs.provisoning;


import java.util.Map;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import com.cheetahapps.sales.tenant.Tenant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class PrepareTenantProvisioningTasklet implements Tasklet{

	

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		log.info("Preparing to start provisioning activities.");
		
		//create fake request context for thread local db handling
		Map<String,Object> jobExecutionContext = chunkContext.getStepContext().getJobExecutionContext();
		Tenant tenant = (Tenant)jobExecutionContext.get("tenant");
		JobRequestAttributes attributes = new JobRequestAttributes();
		attributes.setAttribute("tenantId", tenant.getCode(), JobRequestAttributes.SCOPE_REQUEST);

		RequestContextHolder.setRequestAttributes(attributes);
		
		
		
		//RequestContextHolder.setRequestAttributes(null);
		return RepeatStatus.FINISHED;
	}
	
	
}
