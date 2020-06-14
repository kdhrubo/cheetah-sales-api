package com.cheetahapps.sales.jobs.provisoning;

import java.util.Map;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.cheetahapps.sales.tenant.Tenant;
import com.cheetahapps.sales.tenant.TenantBusinessDelegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateTenantTasklet implements Tasklet {
	
	private final TenantBusinessDelegate tenantBusinessDelegate;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		Map<String,Object> jobParams = chunkContext.getStepContext().getJobParameters();
		String companyName = (String)jobParams.get("company");
		Map<String,Object> jobExecutionContext = chunkContext.getStepContext().getJobExecutionContext();
		ExecutionContext stepContext = contribution.getStepExecution().getExecutionContext();
		
		Tenant tenant = (Tenant)jobExecutionContext.get("tenant");
	
		log.info("Received tenant - {}", tenant);
		
		if(tenant == null) {
			log.info("Creating new tenant");
			Tenant t = tenantBusinessDelegate.create(companyName);
			stepContext.put("tenant", t);
		}
		else {
			log.info("Tenant found. Nothing to do.");
		}
		
		
		return RepeatStatus.FINISHED;
	}

}
