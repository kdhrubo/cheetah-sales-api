package com.cheetahapps.sales.jobs;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import com.cheetahapps.sales.event.ProvisionTenantEvent;
import com.cheetahapps.sales.tenant.Tenant;
import com.cheetahapps.sales.tenant.TenantBusinessDelegate;
import com.cheetahapps.sales.user.UserBusinessDelegate;

import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TenantProvisioningJob {

	private final UserBusinessDelegate userBusinessDelegate;
	private final TenantBusinessDelegate tenantBusinessDelegate;
	private final ApplicationEventPublisher eventPublisher;

	@Scheduled(fixedDelay = 5000)
	public void execute() {
		

		Try.of(() -> provision());

	}

	private Tenant provision() {
		Option<Tenant> tenant = this.tenantBusinessDelegate.findFirstUnProvisioned();

		tenant.onEmpty(() -> 
			log.debug("No new tenant to provision")
		).peek(t -> 
			// get user
			userBusinessDelegate.findByTenantId(t.getId()).onEmpty(() -> 
				log.info("Missing user for new tenant. Admin help required") 
			) //notify slack support failure
			.peek(u -> {
				log.debug("User located. Creating provisioning event");
				
				//create fake request context for thread local db handling
				
				JobRequestAttributes attributes = new JobRequestAttributes();
				attributes.setAttribute("tenantId", t.getCode(), JobRequestAttributes.SCOPE_REQUEST);

				RequestContextHolder.setRequestAttributes(attributes);
				
				this.eventPublisher.publishEvent(ProvisionTenantEvent.of(t, u));
				
				RequestContextHolder.setRequestAttributes(null);
				
				this.tenantBusinessDelegate.updateProvisionStatus(t);
				
				log.debug("Completed provisioning of tenant - {}", t.getName());
			})
			
		);

		return tenant.getOrNull();
	}
}
