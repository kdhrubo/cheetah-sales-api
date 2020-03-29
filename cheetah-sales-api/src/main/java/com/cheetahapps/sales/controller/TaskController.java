package com.cheetahapps.sales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.cheetahapps.sales.business.TaskBusinessDelegate;
import com.cheetahapps.sales.domain.Task;

@RestController
@RequestMapping("/tasks")

public class TaskController extends AbstractBaseController<Task, String> {

	private TaskBusinessDelegate taskBusinessDelegate;

	@Autowired
	public TaskController(TaskBusinessDelegate taskBusinessDelegate) {
		super(taskBusinessDelegate);
		this.taskBusinessDelegate = taskBusinessDelegate;
	}

	@GetMapping("/q")
	public Page<Task> search(@RequestParam("rsql") String rsql, Pageable pageable) {
		return taskBusinessDelegate.search(rsql, pageable);
	}

}