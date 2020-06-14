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
import com.cheetahapps.sales.templates.TemplateBusinessDelegate;
import com.cheetahapps.sales.tenant.Tenant;
import com.cheetahapps.sales.tenant.TenantBusinessDelegate;
import com.cheetahapps.sales.user.User;
import com.cheetahapps.sales.user.UserBusinessDelegate;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class TenantProvisioningJobConfiguration {

	private static final String PROVISION = "provision";
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final TenantBusinessDelegate tenantBusinessDelegate;
	private final UserBusinessDelegate userBusinessDelegate;
	private final PickListBusinessDelegate pickListBusinessDelegate;
	private final TemplateBusinessDelegate mailTemplateBusinessDelegate;
	
	private final CreateUserTasklet createUserTasklet;
	private final CreateTenantTasklet createTenantTasklet;
	private final CheckTenantTasklet checkTenantTasklet;
	private final PrepareTenantProvisioningTasklet prepareTenantProvisioningTasklet;
	private final CleanupTenantProvisioningTasklet cleanupTenantProvisioningTasklet;
	private final SystemMailServerProvisioningTasklet systemMailServerProvisioningTasklet;
	
	
	//provision ROLE
	//provision Forms
	//Provision default price book
	//Send mail processing 
	
	@Bean("tenantProvisioningJob")
	public Job tenantProvisoningJob() {
		return this.jobBuilderFactory.get("tenantProvisoningJob").start(checkTenantStep()).next(createTenantStep())
				.next(createUserStep())
				.next(systemMailServerProvisioningStep())
				.next(prepareTenantProvisioningStep()) //from here data goes to tenant db
				.next(provisionTenantStep())
				.next(provisionUserStep()).next(provisionPickListStep()).next(cleanupTenantProvisioningStep())
				.next(provisionMailTemplateStep())
				.build();
	}
	
	
	
	@Bean
	public Step provisionMailTemplateStep() {
		return stepBuilderFactory.get("cleanupTenantProvisioningStep").tasklet(cleanupTenantProvisioningTasklet).build();
	}
	
	@Bean
	public Step cleanupTenantProvisioningStep() {
		return stepBuilderFactory.get("cleanupTenantProvisioningStep").tasklet(cleanupTenantProvisioningTasklet).build();
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
		return stepBuilderFactory.get("prepareTenantProvisioningStep").tasklet(prepareTenantProvisioningTasklet)
				.listener(promotionListener()).build();
	}
	
	@Bean
	public Step systemMailServerProvisioningStep() {
		return stepBuilderFactory.get("systemMailServerProvisioningStep").tasklet(systemMailServerProvisioningTasklet).listener(promotionListener())
				.build();
	}

	@Bean
	public Step createUserStep() {
		return stepBuilderFactory.get("createUserStep").tasklet(createUserTasklet).listener(promotionListener())
				.build();
	}

	@Bean
	public Step createTenantStep() {
		return stepBuilderFactory.get("createTenantStep").tasklet(createTenantTasklet).listener(promotionListener())
				.build();
	}

	@Bean
	public Step checkTenantStep() {
		return stepBuilderFactory.get("checkTenantStep").tasklet(checkTenantTasklet).listener(promotionListener())
				.build();
	}
	
	@Bean
	public Tasklet provisionMailTemplateTasklet() {
		MethodInvokingTaskletAdapter adapter = new MethodInvokingTaskletAdapter();
		adapter.setTargetObject(this.mailTemplateBusinessDelegate);
		adapter.setTargetMethod(PROVISION);
		return adapter;
	}
	
	
	@Bean
	public Tasklet provisionPickListTasklet() {
		MethodInvokingTaskletAdapter adapter = new MethodInvokingTaskletAdapter();
		adapter.setTargetObject(this.pickListBusinessDelegate);
		adapter.setTargetMethod(PROVISION);
		return adapter;
	}
	
	@StepScope
	@Bean
	public Tasklet provisionUserTasklet(@Value("#{jobExecutionContext['user']}") User user) {
		MethodInvokingTaskletAdapter adapter = new MethodInvokingTaskletAdapter();
		adapter.setTargetObject(this.userBusinessDelegate);
		adapter.setTargetMethod(PROVISION);
		adapter.setArguments(new Object[] { user });
		return adapter;
	}

	@StepScope
	@Bean
	public Tasklet provisionTenantTasklet(@Value("#{jobExecutionContext['tenant']}") Tenant tenant) {
		MethodInvokingTaskletAdapter adapter = new MethodInvokingTaskletAdapter();
		adapter.setTargetObject(this.tenantBusinessDelegate);
		adapter.setTargetMethod(PROVISION);
		adapter.setArguments(new Object[] { tenant });
		return adapter;
	}


	@Bean
	public ExecutionContextPromotionListener promotionListener() {
		ExecutionContextPromotionListener listener = new ExecutionContextPromotionListener();
		listener.setKeys(new String[] { "tenant", "user" });
		return listener;
	}

}
