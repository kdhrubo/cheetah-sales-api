package com.cheetahapps.sales.user;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Qualifier("asyncJobLauncher")
	private final JobLauncher jobLauncher;
    
	@Qualifier("tenantProvisioningJob")
    private final Job job;
	
	
	@PostMapping("/register")
	public UserDto register(@RequestBody UserDto userDto) {
		log.info("Registering user - {}", userDto);
		log.info("jobLauncher - {}", jobLauncher);
		
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("firstName", userDto.getFirstName())
				.addString("lastName", userDto.getLastName())
				.addString("email", userDto.getEmail())
				.addString("password", userDto.getPassword())
				.addString("company", userDto.getCompany())
				.addString("country", userDto.getCountry())
				.toJobParameters();
		
		
		Try.of(() -> jobLauncher.run(job, jobParameters)).onFailure(t -> log.error("Error registering user - {}", t));
		
		
		return userDto;
	}

	
}
