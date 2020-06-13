package com.cheetahapps.sales.jobs.provisoning;

import java.util.Map;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

import com.cheetahapps.sales.tenant.TenantBusinessDelegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CheckTenantTasklet implements Tasklet {

	private final TenantBusinessDelegate tenantBusinessDelegate;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		Map<String,Object> jobParams = chunkContext.getStepContext().getJobParameters();
		
        ExecutionContext stepContext = contribution.getStepExecution().getExecutionContext();

		
		String companyName = (String)jobParams.get("company");
		
		tenantBusinessDelegate.findByName(companyName).onEmpty(() -> log.info("Tenant not found. New tenant"))
		.peek(t -> stepContext.put("tenant", t));
		
		return RepeatStatus.FINISHED;
	}

}
