package com.cheetahapps.sales.core;

import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractController<T, I> {

	protected AbstractBusinessDelegate<T, I> businessDelegate;

	public AbstractController(AbstractBusinessDelegate<T, I> businessDelegate) {
		this.businessDelegate = businessDelegate;
	}

	@PostMapping
	public T save(@Valid @RequestBody T t) {

		return businessDelegate.save(t);

	}

	// view details, edit, copy
	@GetMapping("/findOne/{id}")
	public T findById(@PathVariable("id") I id) {

		log.debug("Id - {}", id);

		return businessDelegate.findById(id).get();

	}

	@GetMapping("/findAll/{deleted}")
	public Page<T> findAllByDeleted(@PathVariable boolean deleted, Pageable pageable) {

		return null;

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
	

	@PostMapping("/copy/{id}")
	public T copy(@PathVariable("id") I id) {
		log.info("Copy object with id = {}", id);
		return businessDelegate.copy(id);
		
	}

}