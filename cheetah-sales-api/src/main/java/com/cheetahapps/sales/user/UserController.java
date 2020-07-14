package com.cheetahapps.sales.user;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheetahapps.sales.core.AbstractController;

import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController extends AbstractController<User, String> {

	private UserBusinessDelegate userBusinessDelegate;
	
	@Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("provisioningJob")
    private Job job;

	public UserController(UserBusinessDelegate userBusinessDelegate) {
		super(userBusinessDelegate);
		this.userBusinessDelegate = userBusinessDelegate;
	}

	@PostMapping("/register")
	public UserDto register(@RequestBody UserDto userDto) {
		log.info("Registering user - {}", userDto);
		
		log.info("userDto - {}", userDto);
		
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("firstName", userDto.getFirstName())
				.addString("lastName", userDto.getLastName())
				.addString("password", userDto.getPassword())
				.addString("company", userDto.getCompany())
				.addString("email", userDto.getEmail())
				.toJobParameters();
		
		Try.of(() -> jobLauncher.run(job, jobParameters));
		
		return userDto;
	}

}
