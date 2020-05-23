package com.cheetahapps.sales.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cheetahapps.sales.core.AbstractController;

@RestController
@RequestMapping("/tasks")

public class TaskController extends AbstractController<Task, String> {

	private TaskBusinessDelegate taskBusinessDelegate;

	@Autowired
	public TaskController(TaskBusinessDelegate taskBusinessDelegate) {
		super(taskBusinessDelegate);
		this.taskBusinessDelegate = taskBusinessDelegate;
	}

	@GetMapping("/q")
	public List<Task> search(@RequestParam("rsql") String rsql) {
		return taskBusinessDelegate.searchAll(rsql);
	}

}