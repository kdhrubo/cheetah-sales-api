package com.cheetahapps.sales.controller;

import javax.validation.Valid;
import org.springframework.context.ApplicationEvent;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cheetahapps.sales.business.AbstractBaseBusinessDelegate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractBaseController<T, I> {

	protected AbstractBaseBusinessDelegate<T, I> businessDelegate;

	public void publishEvent(ApplicationEvent event) {
		businessDelegate.publishEvent(event);
	}

	public AbstractBaseController(AbstractBaseBusinessDelegate<T, I> businessDelegate) {
		this.businessDelegate = businessDelegate;
	}

	@PostMapping
	public T save(@Valid @RequestBody T t) {

		return businessDelegate.save(t);

	}

	// view details, edit, copy
	@GetMapping("/{id}")
	public T findById(@RequestParam("id") I id) {

		log.debug("Id - {}", id);

		return businessDelegate.findById(id).get();

	}

	@RequestMapping("/findAll/{active}")
	public Page<T> findAllByActive(@PathVariable boolean active, Page<T> page) {

		return businessDelegate.findAllByActive(active, page);

	}

	@DeleteMapping("/delete/")
	public void delete(@RequestParam("id") I[] ids) {
		log.debug("Delete multiple object with ids ={} ", ids.length);
		for (I id : ids)
			businessDelegate.delete(id);

	}

	@PostMapping("/purge/{id}")
	public void purge(@PathVariable("id") I id) {
		log.debug("Purge object with id = ", id);
		businessDelegate.purge(id);

	}

	@PostMapping("/restore/{id}")
	public T restore(@PathVariable("id") I id) {
		log.debug("Restore object with id = ", id);

		return businessDelegate.restore(id);

	}

}