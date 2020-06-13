package com.cheetahapps.sales.runner;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TenantProvisioningJobRunner implements ApplicationRunner {
	
	
    private final JobLauncher jobLauncher;
    
    private final Job job;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("=== starting testing provisioning job ==");
		
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("firstName", "Mahatma")
				.addString("lastName", "Gandhi")
				.addString("email", "mahatma@gandhi.org")
				.addString("password", "sample321")
				.addString("company", "DDe Inc.1")
				.toJobParameters();
		
		jobLauncher.run(job, jobParameters);
	}

}
