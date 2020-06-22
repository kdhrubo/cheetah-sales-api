package com.cheetahapps.sales.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.cheetahapps.sales.role.Role;
import com.cheetahapps.sales.tenant.Tenant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private String id;
	
	@NotEmpty
	private String firstName;
	
	@NotEmpty
	private String lastName;
	
	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	@Min(8)
	@Max(12)
	private String password;
	
	@NotEmpty
	private String company;
	
	private String tenantId;
	private String tenantCode;
	private String tenantName;
	
	private boolean deleted;
	
	private Role role;
}
