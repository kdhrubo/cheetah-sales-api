package com.cheetahapps.sales.task;

import java.util.List;
import org.springframework.stereotype.Component;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TaskBusinessDelegate extends AbstractBusinessDelegate<Task, String> {
	
	private TaskRepository repository;
	
	public TaskBusinessDelegate(TaskRepository repository) {
		super(repository);
		this.repository = repository;
	}
	
	
	public List<Task> searchAll(String rsql) {
		log.debug("search");
		return repository.searchAll(rsql, Task.class);
	}
	
}