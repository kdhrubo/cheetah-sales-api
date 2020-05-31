package com.cheetahapps.sales.core;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.scheduling.annotation.Async;

import org.springframework.transaction.annotation.Transactional;

import jodd.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractBusinessDelegate<T, Id> {

	protected MongoRepository<T, Id> repository;

	@Autowired
	protected ApplicationEventPublisher eventPublisher;
	
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
		return repository.save(t);
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
			throw new RuntimeException(
					"Record with id = " + id + " not found. May be it is already deleted or purged. ");
		}

		try {

			BeanUtil.declaredForcedSilent.setProperty(t, "deleted", true);
		} catch (Exception e) {
			log.error("Error while setting deleted property in restore - {}", e);
			throw new RuntimeException("Entity cannot be deleted (soft delete) - " + e.getLocalizedMessage()
					+ ". Check if deleted field is present.");
		}
		repository.save(t.get());

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

			BeanUtil.declaredForcedSilent.setProperty(t, "deleted", false);
		} catch (Exception e) {
			log.error("Error while setting deleted property in restore - {}", e);
			throw new RuntimeException("Entity cannot be deleted (soft delete) - " + e.getLocalizedMessage()
					+ ". Check if deleted field is present.");
		}
		return repository.save(t);
	}

}