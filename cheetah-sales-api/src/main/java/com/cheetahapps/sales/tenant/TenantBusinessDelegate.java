package com.cheetahapps.sales.tenant;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Objects;

import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.event.ProvisionTenantEvent;

import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TenantBusinessDelegate extends AbstractBusinessDelegate<Tenant, String> {

	private final TenantRepository tenantRepository;
	

	public TenantBusinessDelegate(TenantRepository tenantRepository) {
		super(tenantRepository);
		this.tenantRepository = tenantRepository;
	}

	@Transactional(readOnly = true)
	public Option<Tenant> findByName(String name) {
		return this.tenantRepository.findByName(name);
	}
	
	@Transactional
	@EventListener
	public void provision(ProvisionTenantEvent event) {
		log.info("Provisioning tenant");
		
		if(!event.isExistingTenant()) {
			log.info("Tenant is being provisioned.");
			save(event.getTenant());
		}
		
	}
	
	
	/**
	 * Allows creation of a new tenant which is not provisioned.
	 * @param tenantName
	 * @return
	 */
	@Transactional
	public Tenant create(String tenantName) {
		Tenant t = Tenant.builder().name(tenantName).code("tenant-" + getTenantSeq()).build();
		return save(t);
	}


	@Transactional(readOnly = true)
	public TenantView findByCode(String code) {
		return tenantRepository.findByCode(code);
	}
	
	
	
	private long getTenantSeq() {
		TenantSequence counter = getMongoOperations().findAndModify(query(where("_id").is("tenant_sequence")),
				new Update().inc("seq", 1), options().returnNew(true).upsert(true), TenantSequence.class);
		return !Objects.isNull(counter) ? counter.getSeq() : 1;
	}

}
