package com.cheetahapps.sales.service;

import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cheetahapps.sales.domain.Role;
import com.cheetahapps.sales.domain.User;
import com.cheetahapps.sales.dto.UserView;
import com.cheetahapps.sales.integration.AwsEmailSender;
import com.cheetahapps.sales.integration.SlackMessageSender;
import com.cheetahapps.sales.repository.RoleRepository;
import com.cheetahapps.sales.repository.UserRepository;
import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator;

import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	private final SlackMessageSender slackMessageSender;

	private final TimeBasedOneTimePasswordGenerator totp;
	private final KeyGenerator keyGenerator;

	private final AwsEmailSender awsEmailSender;

	public User register(User user) {

		log.info("Registering user - {}", user.getEmail());

		Role role = roleRepository.findByName(Role.USER);

		user.setRole(role);

		String password = user.getPassword();
		user.setPassword(passwordEncoder.encode(password));

		User u = this.userRepository.save(user);

		slackMessageSender.send("New user created - " + u.getEmail() + " , Name - " + u.getFullName());

		return u;
	}

	public Option<UserView> getById(String id) {
		return this.userRepository.getById(id);
	}

	public User generateOtp(String email) throws Exception {

		Option<User> user = this.userRepository.findByEmail(email);

		if (user.isEmpty()) {
			throw new RuntimeException("User with given email does not exist");

		}

		Key key = keyGenerator.generateKey();
		Instant now = Instant.now();
		int pwd = totp.generateOneTimePassword(key, now);

		User u = user.get();
		u.setVerificationCode(pwd + "");
		u.setVerificationCodeCreatedDate(LocalDateTime.now());

		Map<String, String> data = new HashMap<>();
		data.put("otp", pwd + "");

		awsEmailSender.send("dhrubo.kayal@gmail.com", "BongReads - Forgot Password? Verification Code", "welcome",
				data);

		return this.userRepository.save(u);
	}

	public User updatePassword(String email, String verificationCode, String password) {
		Option<User> user = this.userRepository.findByEmail(email);

		if (user.isEmpty()) {
			throw new RuntimeException("User with given email does not exist");

		}

		User u = user.get();

		if (!u.getVerificationCode().equals(verificationCode)) {
			throw new RuntimeException("Failed to match verificaiton code.");
		}

		u.setPassword(passwordEncoder.encode(password));

		return this.userRepository.save(u);
	}

}
