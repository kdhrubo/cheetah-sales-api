package com.cheetahapps.sales.event;

import org.springframework.context.ApplicationEvent;

import com.cheetahapps.sales.domain.User;

import lombok.Getter;

public class ProvisionTenantEvent extends ApplicationEvent {
	@Getter
	private User user;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProvisionTenantEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	public ProvisionTenantEvent(User user, Object source) {
		super(source);
		this.user = user;
	}

}
