package com.cheetahapps.sales.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cheetahapps.sales.domain.Task;


@Repository
public interface TaskRepository extends MongoRepository<Task, String>, SearchRepositoryCustom<Task> {
	
	List<Task> findByRelatedToAndRelatedToId(String relatedTo, String relatedToId);

}
