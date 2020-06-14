package com.cheetahapps.sales.jobs.provisoning;

import java.util.Map;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.cheetahapps.sales.role.RoleBusinessDelegate;
import com.cheetahapps.sales.tenant.Tenant;
import com.cheetahapps.sales.user.User;
import com.cheetahapps.sales.user.UserBusinessDelegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateUserTasklet implements Tasklet {

	private final UserBusinessDelegate userBusinessDelegate;
	private final RoleBusinessDelegate roleBusinessDeleage;
	

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		Map<String,Object> jobParams = chunkContext.getStepContext().getJobParameters();
		String firstName = (String) jobParams.get("firstName");
		String lastName = (String) jobParams.get("lastName");
		String email = (String) jobParams.get("email");
		String password = (String) jobParams.get("password");
		
		ExecutionContext stepContext = contribution.getStepExecution().getExecutionContext();
		
		
		Map<String, Object> jobExecutionContext = chunkContext.getStepContext().getJobExecutionContext();

		Tenant tenant = (Tenant) jobExecutionContext.get("tenant");
		log.info("Received tenant - {}", tenant);
		
		User u;
		
		if(tenant.isProvisioned()) {
			log.info("Existing tenant. Create a team member user");
			u = this.userBusinessDelegate.create(firstName, lastName, email, password, 
					tenant.getId(), tenant.getCode(), tenant.getName(), roleBusinessDeleage.getTeamMember());
		}
		else {
			log.info("New tenant. create company admin user");
			
			u = this.userBusinessDelegate.create(firstName, lastName, email, password, 
					tenant.getId(), tenant.getCode(), tenant.getName(), roleBusinessDeleage.getTenantAdmin());
		}
		
		stepContext.put("user", u);
		
		return RepeatStatus.FINISHED;
	}

}
