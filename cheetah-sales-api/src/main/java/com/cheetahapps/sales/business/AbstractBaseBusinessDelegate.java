package com.cheetahapps.sales.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Async;

import org.springframework.transaction.annotation.Transactional;

import com.cheetahapps.sales.repository.BaseMongoRepository;

import jodd.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractBaseBusinessDelegate<T, Id> {

	protected BaseMongoRepository<T, Id> repository;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Async
	public void publishEvent(ApplicationEvent event) {
		eventPublisher.publishEvent(event);
	}

	public AbstractBaseBusinessDelegate(BaseMongoRepository<T, Id> repository) {

		this.repository = repository;

	}

	// create , update
	@Transactional
	public T save(T t) {
		T r = repository.save(t);
		return r;
	}

	// read
	@Transactional(readOnly = true)
	public Optional<T> findById(Id id) {
		return repository.findById(id);
	}
	
	//read all which are active/inactive (recycle bin)
	public Page<T> findAllByDeleted(boolean deleted, Page<T> page) {
		return repository.findAllByDeleted(deleted, page);
	}

	// delete
	@Transactional
	public void delete(Id id) {

		T t = findById(id).get();

		if (t == null) {
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
		repository.save(t);

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