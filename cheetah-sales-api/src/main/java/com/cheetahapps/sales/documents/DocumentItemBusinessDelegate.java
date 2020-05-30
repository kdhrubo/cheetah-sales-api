package com.cheetahapps.sales.documents;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.vavr.control.Option;
import jodd.util.StringUtil;

import com.cheetahapps.sales.activity.Activity;
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

	
	
	public List<DocumentItem> searchAll(String rsql) {
		//"firstName==Paul;age==30"
		//"deleted==false"
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, DocumentItem.class);
	    Criteria criteria = condition.query(new MongoVisitor());
		
		return documentItemRepository.searchAll(criteria, DocumentItem.class);
	}

	@Transactional
	public DocumentItem createFolder(CreateDocumentItemRequest request) {
		CreateFolderEvent event = null;
		if (StringUtil.isEmpty(request.getParentId())) {
			
			log.info("Parent id -null");
			
			event = CreateFolderEvent.builder().name(request.getName()).externalParentId(Option.none())
					.documentSource(request.getDocumentSource()).documentSourceId(request.getDocumentSourceId())
					.build();
		} else {
			
			log.info("Parent id - {}", request.getParentId());
			
			DocumentItem parent = this.documentItemRepository.findById(request.getParentId()).get(); // handle error
																										// parent folder
			log.info("Parent id - {}", parent);																							// not found

			event = CreateFolderEvent.builder().name(request.getName())
					.externalParentId(Option.of(parent.getExternalParentId()))
					.documentSource(request.getDocumentSource()).documentSourceId(request.getDocumentSourceId())
					.build();

		}

		this.eventPublisher.publishEvent(event);

		log.info("Event - {}", event.getExternalParentName().getOrNull());
		

		DocumentItem item = DocumentItem.builder().documentSource(event.getDocumentSource()).parentName(request.getParentName())
				.documentSourceId(event.getDocumentSourceId()).documentType(request.getDocumentType())
				.externalId(event.getExternalId()).externalParentId(event.getExternalParentId().getOrNull())
				.externalParentName(event.getExternalParentName().getOrNull()).name(request.getName())
				.build();

		return documentItemRepository.save(item);

	}

	public DocumentItem createExternalFileLink(String title, String parentId, String link, String documentType,
			String documentTypeId) {
		DocumentItem item = DocumentItem.builder().documentType(documentType).documentTypeId(documentTypeId)

				.externalParentId(parentId).name(title).title(title).build();

		return documentItemRepository.save(item);

	}
}
