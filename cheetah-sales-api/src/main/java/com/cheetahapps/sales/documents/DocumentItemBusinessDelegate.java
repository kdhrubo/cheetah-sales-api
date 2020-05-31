package com.cheetahapps.sales.documents;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.vavr.control.Option;
import jodd.util.StringUtil;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.github.rutledgepaulv.qbuilders.builders.GeneralQueryBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.visitors.MongoVisitor;
import com.github.rutledgepaulv.rqe.pipes.QueryConversionPipeline;

import lombok.extern.slf4j.Slf4j;

//TODO Better error chekcs and handling required.

@Slf4j
@Component
class DocumentItemBusinessDelegate extends AbstractBusinessDelegate<DocumentItem, String> {

	private DocumentItemRepository documentItemRepository;

	public DocumentItemBusinessDelegate(DocumentItemRepository documentItemRepository) {
		super(documentItemRepository);
		this.documentItemRepository = documentItemRepository;
	}

	public List<DocumentItem> searchAll(String rsql) {
		QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, DocumentItem.class);
		Criteria criteria = condition.query(new MongoVisitor());

		return documentItemRepository.searchAll(criteria, DocumentItem.class);
	}

	@Transactional
	public DocumentItem createFolder(CreateDocumentItemRequest request) {
		CreateFolderEvent event = null;
		if (StringUtil.isEmpty(request.getParentId())) {

			
			event = CreateFolderEvent.builder().name(request.getName()).externalParentId(Option.none())
					.documentSource(request.getDocumentSource()).documentSourceId(request.getDocumentSourceId())
					.build();
			this.publish(event);
			
		} else {

			
			DocumentItem parent = this.documentItemRepository.findById(request.getParentId()).get(); // handle error
																										// parent folder
			

			event = CreateFolderEvent.builder().name(request.getName())
					.externalParentId(Option.of(parent.getExternalParentId()))
					.documentSource(request.getDocumentSource())
					.documentSourceId(request.getDocumentSourceId())
					.build();
			this.publish(event);
		}


		DocumentItem item = DocumentItem.builder().documentSource(event.getDocumentSource())
				.parentName(request.getParentName()).documentSourceId(event.getDocumentSourceId())
				.documentType("folder").externalId(event.getExternalId())
				.externalParentId(event.getExternalParentId().getOrNull())
				.externalParentName(event.getExternalParentName().getOrNull()).name(request.getName()).build();

		return documentItemRepository.save(item);

	}
	
	@Transactional
	public DocumentItem createExternalFileLink(String title, String parentId, String link, String documentType,
			String documentTypeId) {
		DocumentItem item = DocumentItem.builder().documentType(documentType).documentTypeId(documentTypeId)

				.externalParentId(parentId).name(title).title(title).build();

		return documentItemRepository.save(item);

	}
	
	@Transactional
	public DocumentItem createFile(CreateMultipartDocumentItemRequest request) {
		log.info("Request - {}", request);
		
		
		String fileNameOriginal = request.getFile().getOriginalFilename();
		
		
		log.info("fileNameOriginal - {}", fileNameOriginal);
		
		String extension = fileNameOriginal.substring(fileNameOriginal.lastIndexOf('.') + 1);
		log.info("extension - {}", extension);

		CreateFileEvent event = null;
		if (StringUtil.isEmpty(request.getParentId())) {

			log.info("Parent id -null");

			event = CreateFileEvent.builder().name(fileNameOriginal).externalParentId(Option.none())
					.file(request.getFile())
					.documentSource(request.getDocumentSource()).documentSourceId(request.getDocumentSourceId())
					.build();
			
			this.publish(event);
			
		} else {

			log.info("Parent id - {}", request.getParentId());

			DocumentItem parent = this.documentItemRepository.findById(request.getParentId()).get(); // handle error
																										// parent folder
			log.info("Parent id - {}", parent); // not found

			event = CreateFileEvent.builder().name(fileNameOriginal)
					.file(request.getFile())
					.externalParentId(Option.of(parent.getExternalParentId()))
					.documentSource(request.getDocumentSource()).documentSourceId(request.getDocumentSourceId())
					.build();
			
			this.publish(event);

		}

	
		log.info("Event - {}", event);

		DocumentItem item = DocumentItem.builder().documentSource(event.getDocumentSource())
				.parentName(request.getParentName()).documentSourceId(event.getDocumentSourceId()).documentType("file")
				.extension(extension)
				.externalId(event.getExternalId()).externalParentId(event.getExternalParentId().getOrNull())
				.externalParentName(event.getExternalParentName().getOrNull()).name(fileNameOriginal).build();

		return documentItemRepository.save(item);

	}
}
