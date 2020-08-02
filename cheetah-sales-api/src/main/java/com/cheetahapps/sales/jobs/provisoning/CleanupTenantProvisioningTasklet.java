package com.cheetahapps.sales.jobs.provisoning;


import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CleanupTenantProvisioningTasklet implements Tasklet{

	

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		
		
		
		log.info("Ending provisioning activities.");
		
		
		
		RequestContextHolder.setRequestAttributes(null);
		
		
		
		return RepeatStatus.FINISHED;
	}
	
	
}
