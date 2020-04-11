package com.cheetahapps.sales.note;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cheetahapps.sales.repository.SearchRepositoryCustom;

interface NoteRepository extends MongoRepository<Note, String>, SearchRepositoryCustom<Note> {

	List<Note> findAllByRelatedEntityAndRelatedEntityId(String relatedEntity, String relatedEntityId);

}
