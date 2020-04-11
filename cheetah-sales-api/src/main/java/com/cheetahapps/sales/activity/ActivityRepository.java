package com.cheetahapps.sales.activity;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.cheetahapps.sales.repository.SearchRepositoryCustom;

interface ActivityRepository extends MongoRepository<Activity, String>, SearchRepositoryCustom<Activity> {

}
