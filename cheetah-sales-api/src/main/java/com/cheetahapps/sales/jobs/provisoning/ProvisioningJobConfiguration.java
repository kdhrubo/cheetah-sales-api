package com.cheetahapps.sales.jobs.provisoning;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.listener.ExecutionContextPromotionListener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class ProvisioningJobConfiguration {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	private final RegisterTasklet registerTasklet;
	private final PrepareTenantProvisioningTasklet prepareTenantProvisioningTasklet;
	private final SendRegistrationConfirmationEmailTasklet sendRegistrationConfirmationEmailTasklet;
	private final CleanupTenantProvisioningTasklet cleanupTenantProvisioningTasklet;

	@Bean("provisioningJob")
	public Job provisoningJob() {
		return this.jobBuilderFactory.get("provisioningJob").start(registerStep()).next(prepareTenantProvisioningStep())
				.next(sendConfirmationEmailStep())
				.next(cleanupTenantProvisioningStep()).build();
	}

	@Bean
	public Step registerStep() {
		return stepBuilderFactory.get("registerStep").tasklet(registerTasklet).listener(promotionListener()).build();
	}

	@Bean
	public Step prepareTenantProvisioningStep() {
		return stepBuilderFactory.get("prepareTenantProvisioningStep").tasklet(prepareTenantProvisioningTasklet)
				.listener(promotionListener()).build();
	}
	
	@Bean
	public Step sendConfirmationEmailStep() {
		return stepBuilderFactory.get("sendConfirmationEmailStep").tasklet(sendRegistrationConfirmationEmailTasklet)
				.build();
	}

	@Bean
	public Step cleanupTenantProvisioningStep() {
		return stepBuilderFactory.get("cleanupTenantProvisioningStep").tasklet(cleanupTenantProvisioningTasklet)
				.build();
	}

	@Bean
	public ExecutionContextPromotionListener promotionListener() {
		ExecutionContextPromotionListener listener = new ExecutionContextPromotionListener();
		listener.setKeys(new String[] { "tenant", "user", "existingTenant" });
		return listener;
	}

}
