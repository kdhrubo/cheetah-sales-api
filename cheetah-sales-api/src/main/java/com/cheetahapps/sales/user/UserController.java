package com.cheetahapps.sales.user;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cheetahapps.sales.core.AbstractController;
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
	public UserDto register(@RequestBody UserDto userDto) throws Exception{
		log.info("Registering user - {}", userDto.getFirstName());
		
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("firstName", userDto.getFirstName())
				.addString("lastName", userDto.getLastName())
				.addString("password", userDto.getPassword())
				.addString("company", userDto.getCompany())
				.addString("email", userDto.getEmail())
				.toJobParameters();
		
		jobLauncher.run(job, jobParameters);
		
		userDto.setPassword(null);
		
		return userDto;
	}
	
	@PostMapping("/add")
	public UserDto add(@RequestBody UserDto userDto) throws Exception{
		this.userBusinessDelegate.add(userDto);
		userDto.setPassword(null);
		return userDto;
	}
	
	@GetMapping("/q")
	public Page<User> search(@RequestParam("rsql") String rsql, @PageableDefault Pageable pageable) {
		return this.userBusinessDelegate.search(rsql, pageable);
	}

}
