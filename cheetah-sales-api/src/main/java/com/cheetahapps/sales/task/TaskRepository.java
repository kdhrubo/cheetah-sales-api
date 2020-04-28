package com.cheetahapps.sales.task;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.cheetahapps.sales.core.SearchRepositoryCustom;

public interface TaskRepository extends MongoRepository<Task, String>, SearchRepositoryCustom<Task> {
	

}
