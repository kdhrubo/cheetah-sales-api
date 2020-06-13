package com.cheetahapps.sales.jobs.provisoning;

import java.util.Map;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.cheetahapps.sales.tenant.Tenant;
import com.cheetahapps.sales.tenant.TenantBusinessDelegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
@Deprecated
public class ProvisionTenantTasklet implements Tasklet {
	
	private final TenantBusinessDelegate tenantBusinessDelegate;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		Map<String,Object> jobExecutionContext = chunkContext.getStepContext().getJobExecutionContext();
		Tenant tenant = (Tenant)jobExecutionContext.get("tenant");
		
		tenantBusinessDelegate.provision(tenant);
		
		return RepeatStatus.FINISHED;
	}

}
