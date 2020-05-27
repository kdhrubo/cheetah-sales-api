package com.cheetahapps.sales.documents;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.vavr.control.Option;
import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.github.rutledgepaulv.qbuilders.builders.GeneralQueryBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.visitors.MongoVisitor;
import com.github.rutledgepaulv.rqe.pipes.QueryConversionPipeline;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
class DocumentItemBusinessDelegate extends AbstractBusinessDelegate<DocumentItem, String> {
	
	private DocumentItemRepository documentItemRepository;
	
	private QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();
	
	public DocumentItemBusinessDelegate(DocumentItemRepository documentItemRepository) {
		super(documentItemRepository);
		this.documentItemRepository = documentItemRepository;
	}
	
	
	public Page<DocumentItem> search(String rsql, Pageable pageable) {
		//"firstName==Paul;age==30"
		//"deleted==false"
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, DocumentItem.class);
	    Criteria criteria = condition.query(new MongoVisitor());
		
		return documentItemRepository.search(criteria, pageable, DocumentItem.class);
	}
	
	@Transactional
	public DocumentItem createFolder( String parentId, String folder, String documentSource, String documentSourceId, String documentType, String documentTypeId) {
		CreateFolderEvent event = CreateFolderEvent.builder().parent(Option.of(parentId))
				.folder(folder).documentSource(documentSource).documentSourceId(documentSourceId).
				build();
		this.eventPublisher.publishEvent(event);
		
		log.info("Event - {}", event);
		
		DocumentItem item = DocumentItem.builder().documentSource(documentSourceId)
				.documentSourceId(documentSourceId)
				.documentType(documentType)
				.documentTypeId(documentTypeId)
				.externalId(event.getExternalId())
				.externalParentId(event.getExternalParentId())
				.externalParentName(event.getExternalParentName())
				.name(folder)
				.title(folder).build();
		
		
		return documentItemRepository.save(item);
	}
	
	public DocumentItem createExternalFileLink(String title, String parentId, String link, String documentType, String documentTypeId) {
		DocumentItem item = DocumentItem.builder()
				.documentType(documentType)
				.documentTypeId(documentTypeId)
				
				.externalParentId(parentId)
				.name(title)
				.title(title).build();
		
		
		return documentItemRepository.save(item);
		
		
	}
}




