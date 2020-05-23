package com.cheetahapps.sales.event;


import com.cheetahapps.sales.tenant.Tenant;
import com.cheetahapps.sales.user.User;
import lombok.Value;
@Value(staticConstructor = "of")
public class ProvisionTenantEvent  {
	
	private Tenant tenant;
	private User user;
	

}
