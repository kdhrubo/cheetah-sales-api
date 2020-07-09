package com.cheetahapps.sales.core;



import lombok.Data;


@Data
public class AuthenticatedUser {
	private String userId;
	private String firstName; 
	private String lastName;
	private String email;
	
	private String tenantId;
	private String tenantCode;
	
	public void clear() {
		this.userId = null;
		this.firstName = null;
		this.lastName = null;
		this.email = null;
		this.tenantId = null;
		this.tenantCode = null;
	}
}
