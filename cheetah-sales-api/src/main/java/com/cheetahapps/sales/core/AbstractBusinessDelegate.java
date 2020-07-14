package com.cheetahapps.sales.core;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.scheduling.annotation.Async;

import org.springframework.transaction.annotation.Transactional;

import com.cheetahapps.sales.problem.NoDataFoundProblem;

import jodd.bean.BeanUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractBusinessDelegate<T, Id> {

	private static final String DELETED = "deleted";
	protected MongoRepository<T, Id> repository;
	@Getter
	@Autowired
	private MongoOperations mongoOperations;
	
	@Getter
	@Autowired
	private AuthenticatedUser authUser;

	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	public void publish(Object event) {

		eventPublisher.publishEvent(event);
	}

	@Async
	public void publishAsync(Object event) {

		eventPublisher.publishEvent(event);
	}

	public AbstractBusinessDelegate(MongoRepository<T, Id> repository) {

		this.repository = repository;

	}

	// create , update
	@Transactional
	public T save(T t) {
		beforeSave(t);
		log.info("Authenticated user - {}", this.authUser);
		T saved = repository.save(t);
		afterSave(t);
		return saved;
	}

	// read
	@Transactional(readOnly = true)
	public Optional<T> findById(Id id) {
		return repository.findById(id); // better error handling required with problem spec
	}
	
	

	// bulk create, import
	@Transactional
	public List<T> saveAll(List<T> ts) {
		return this.repository.saveAll(ts);
	}

	// delete
	@Transactional
	public void delete(Id id) {

		Optional<T> t = findById(id);

		if (!t.isPresent()) {
			throw new NoDataFoundProblem(
					"Record with id = " + id + " not found. May be it is already deleted or purged. ");
		}
		
		T obj = t.get();
		
		beforeDelete(obj);

		try {
			
			BeanUtil.declaredForcedSilent.setProperty(obj, DELETED, true);
			
			
		} catch (Exception e) {
			log.error("Error while setting deleted property in restore - {}", e);
			throw new RuntimeException("Entity cannot be deleted.");
		}
		
		log.info("Before delete - {}", obj);
		
		repository.save(obj);
		
		afterDelete(obj);

	}

	@Transactional
	public void purge(Id id) {
		repository.deleteById(id);
	}

	@Transactional
	public void deleteAll(Id[] ids) {
		// Since we are getting unique id of the entity we do not require to use
		// the parent id here.
		for (Id id : ids) {
			delete(id);
		}

	}

	@Transactional
	public T restore(Id id) {

		T t = findById(id).get();

		if (t == null) {
			throw new RuntimeException(
					"Record with id = " + id + " not found. May be it is already deleted or purged. ");
		}

		try {

			BeanUtil.declaredForcedSilent.setProperty(t, DELETED, false);
		} catch (Exception e) {
			log.error("Error while setting deleted property in restore - {}", e);
			throw new RuntimeException("Entity cannot be deleted (soft delete) - " + e.getLocalizedMessage()
					+ ". Check if deleted field is present.");
		}
		return repository.save(t);
	}
	
	@Transactional
	public T copy(Id id) {
		Optional<T> t = findById(id);

		if (!t.isPresent()) {
			throw new NoDataFoundProblem(
					"Record with given id not found.");
		}
		
		T copied = t.get();
		
		//try
		
		BeanUtil.declaredSilent.setProperty(copied, "id", null);
		BeanUtil.declaredSilent.setProperty(copied, "createdDate", null);
		BeanUtil.declaredSilent.setProperty(copied, "lastModifiedDate", null);
		BeanUtil.declaredSilent.setProperty(copied, DELETED, false);
		
		beforeCopy(copied);
		
		save(copied);
		
		afterCopy(copied);
		
		return copied;
	}
	
	
	
	protected void beforeSave(T t) {}
	protected void afterSave(T t) {}
	protected void beforeCopy(T t) {}
	protected void afterCopy(T t) {}
	
	protected void beforeDelete(T t) {}
	protected void afterDelete(T t) {}

}