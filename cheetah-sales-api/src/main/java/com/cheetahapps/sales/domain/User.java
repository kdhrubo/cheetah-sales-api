package com.cheetahapps.sales.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;



@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document
public class User {
	
	@Id
	private String id;
	private String email;
	private String mobile;
	
	
	private String password;
	private String firstName;
	private String lastName;
	
	@DBRef
	private Role role;
	
	
	
	
	private boolean deleted;

	@LastModifiedDate
	private LocalDateTime lastModifiedDate;
	
	@CreatedDate
	private LocalDateTime createdDate;

	private LocalDateTime lastLoginDate;
	
	private String verificationCode;
	
	private LocalDateTime verificationCodeCreatedDate;
	
}
