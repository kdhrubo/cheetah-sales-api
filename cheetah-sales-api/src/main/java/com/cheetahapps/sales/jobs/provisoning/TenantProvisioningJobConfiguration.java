package com.cheetahapps.sales.jobs.provisoning;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.listener.ExecutionContextPromotionListener;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cheetahapps.sales.picklist.PickListBusinessDelegate;
import com.cheetahapps.sales.role.RoleBusinessDelegate;
import com.cheetahapps.sales.tenant.Tenant;
import com.cheetahapps.sales.tenant.TenantBusinessDelegate;
import com.cheetahapps.sales.user.User;
import com.cheetahapps.sales.user.UserBusinessDelegate;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class TenantProvisioningJobConfiguration {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final TenantBusinessDelegate tenantBusinessDelegate;
	private final UserBusinessDelegate userBusinessDelegate;
	private final RoleBusinessDelegate roleBusinessDelegate;
	private final PickListBusinessDelegate pickListBusinessDelegate;

	@Bean("tenantProvisioningJob2")
	public Job tenantProvisoningJob() {
		return this.jobBuilderFactory.get("tenantProvisoningJob2").start(checkTenantStep()).next(createTenantStep())
				.next(createUserStep()).next(prepareTenantProvisioningStep()).next(provisionTenantStep())
				.next(provisionUserStep()).next(provisionPickListStep())
				.build();
	}

	
	@Bean
	public Step provisionPickListStep() {
		return stepBuilderFactory.get("provisionPickListStep").tasklet(provisionPickListTasklet()).build();
	}
	
	
	@Bean
	public Step provisionUserStep() {
		return stepBuilderFactory.get("provisionUserStep").tasklet(provisionUserTasklet(null)).build();
	}

	@Bean
	public Step provisionTenantStep() {
		return stepBuilderFactory.get("provisionTenantStep").tasklet(provisionTenantTasklet(null)).build();
	}

	@Bean
	public Step prepareTenantProvisioningStep() {
		return stepBuilderFactory.get("prepareTenantProvisioningStep").tasklet(prepareTenantProvisioningTasklet())
				.listener(promotionListener()).build();
	}

	@Bean
	public Step createUserStep() {
		return stepBuilderFactory.get("createUserStep").tasklet(createUserTasklet()).listener(promotionListener())
				.build();
	}

	@Bean
	public Step createTenantStep() {
		return stepBuilderFactory.get("createTenantStep").tasklet(createTenantTasklet()).listener(promotionListener())
				.build();
	}

	@Bean
	public Step checkTenantStep() {
		return stepBuilderFactory.get("checkTenantStep").tasklet(checkTenantTasklet()).listener(promotionListener())
				.build();
	}
	
	@Bean
	public Tasklet provisionPickListTasklet() {
		MethodInvokingTaskletAdapter adapter = new MethodInvokingTaskletAdapter();
		adapter.setTargetObject(this.pickListBusinessDelegate);
		adapter.setTargetMethod("provision");
		return adapter;
	}
	
	@StepScope
	@Bean
	public Tasklet provisionUserTasklet(@Value("#{jobExecutionContext['user']}") User user) {
		MethodInvokingTaskletAdapter adapter = new MethodInvokingTaskletAdapter();
		adapter.setTargetObject(this.userBusinessDelegate);
		adapter.setTargetMethod("provision");
		adapter.setArguments(new Object[] { user });
		return adapter;
	}

	@StepScope
	@Bean
	public Tasklet provisionTenantTasklet(@Value("#{jobExecutionContext['tenant']}") Tenant tenant) {
		MethodInvokingTaskletAdapter adapter = new MethodInvokingTaskletAdapter();
		adapter.setTargetObject(this.tenantBusinessDelegate);
		adapter.setTargetMethod("provision");
		adapter.setArguments(new Object[] { tenant });
		return adapter;
	}

	/*
	 * @Bean public ProvisionTenantTasklet provisionTenantTasklet() { return new
	 * ProvisionTenantTasklet(this.tenantBusinessDelegate); }
	 */

	@Bean
	public PrepareTenantProvisioningTasklet prepareTenantProvisioningTasklet() {
		return new PrepareTenantProvisioningTasklet();
	}

	@Bean
	public CheckTenantTasklet checkTenantTasklet() {
		return new CheckTenantTasklet(this.tenantBusinessDelegate);
	}

	@Bean
	public CreateTenantTasklet createTenantTasklet() {
		return new CreateTenantTasklet(this.tenantBusinessDelegate);
	}

	@Bean
	public CreateUserTasklet createUserTasklet() {
		return new CreateUserTasklet(this.userBusinessDelegate, this.roleBusinessDelegate);
	}

	@Bean
	public ExecutionContextPromotionListener promotionListener() {
		ExecutionContextPromotionListener listener = new ExecutionContextPromotionListener();
		listener.setKeys(new String[] { "tenant", "user" });
		return listener;
	}

}
