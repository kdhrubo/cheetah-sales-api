package com.cheetahapps.sales.tenant;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Objects;

import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;

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
	public void provision(Tenant tenant) {
		log.info("Provisioning tenant");
		save(tenant);
	}

	@Transactional
	public void markAsProvisioned(Tenant t) {
		t.setProvisioned(true);
		save(t);
	}

	@Transactional(readOnly = true)
	public TenantView findByCode(String code) {
		return tenantRepository.findByCode(code);
	}
	
	public Tenant create(String tenantName) {
		Tenant t = Tenant.builder().name(tenantName).code("T_" + getTenantSeq()).build();
		return this.tenantRepository.save(t);
	}
	
	private long getTenantSeq() {
		TenantSequence counter = getMongoOperations().findAndModify(query(where("_id").is("tenant_sequence")),
				new Update().inc("seq", 1), options().returnNew(true).upsert(true), TenantSequence.class);
		return !Objects.isNull(counter) ? counter.getSeq() : 1;
	}

}
