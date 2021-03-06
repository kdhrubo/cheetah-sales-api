package com.cheetahapps.sales.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cheetahapps.sales.role.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document("User")
@TypeAlias("User")
public class User implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	@Indexed(unique = true)
	private String email;
	private String mobile;
	
	@JsonIgnore
	private String password;
	private String firstName;
	private String lastName;
	
	@JsonUnwrapped(prefix = "role")
	private Role role;

	private String tenantId;
	private String tenantName;
	private String tenantCode;

	private boolean deleted;

	@LastModifiedDate
	private LocalDateTime lastModifiedDate;

	@CreatedDate
	private LocalDateTime createdDate;
	
	private UserType type;
	private List<User> relatedMembers; //if type = TEAM then members are here.
}

enum UserType {
	TEAM,
	MEMBER
}
