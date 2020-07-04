package com.cheetahapps.sales.document;

import java.util.List;

import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.vavr.control.Option;
import jodd.util.StringUtil;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.event.ProvisionTenantEvent;
import com.cheetahapps.sales.problem.DuplicateDataProblem;
import com.github.rutledgepaulv.qbuilders.builders.GeneralQueryBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.visitors.MongoVisitor;
import com.github.rutledgepaulv.rqe.pipes.QueryConversionPipeline;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
class DocumentItemBusinessDelegate extends AbstractBusinessDelegate<DocumentItem, String> {

	private DocumentItemRepository documentItemRepository;

	public DocumentItemBusinessDelegate(DocumentItemRepository documentItemRepository) {
		super(documentItemRepository);
		this.documentItemRepository = documentItemRepository;
	}

	@EventListener
	public void provision(ProvisionTenantEvent event)  {
		if (!event.isExistingTenant()) {
			publish(CreateRootEvent.of(event.getTenant().getCode()));
		}
	}

	@Transactional
	public DocumentItem createFolder(CreateFolderRequest request) {
		log.info("Container - {}", request.getContainer());
		String container = request.getContainer();
		String path = container + "/" + request.getName();
		
		if(StringUtil.equals(container, "/")) {
			path = container + request.getName();
		}
		
		// check if folder exists
		Option<DocumentItem> folder = this.documentItemRepository
				.findByPath(path);
		
		if(folder.isEmpty()) {
			//create folder in this container / parent folder
			CreateFolderEvent event = CreateFolderEvent.builder().name(request.getName()).root(request.getRoot())
					.container(request.getContainer()).build();
			
			publish(event);
			
			DocumentItem item = DocumentItem.builder().name(request.getName()).container(container).type(DocType.FOLDER)
					.path(path).build();
			
			return documentItemRepository.save(item);
		}
		else {
			throw new DuplicateDataProblem("Folder already exists");
		}
		
		
	}

	@Transactional(readOnly = true)
	public List<DocumentItem> searchAll(String rsql) {
		log.info("rsql - {}", rsql);
		QueryConversionPipeline pipeline = QueryConversionPipeline.defaultPipeline();
		Condition<GeneralQueryBuilder> condition = pipeline.apply(rsql, DocumentItem.class);
		Criteria criteria = condition.query(new MongoVisitor());

		return documentItemRepository.searchAll(criteria, DocumentItem.class);
	}

	/*
	 * @Transactional public DocumentItem createExternalFileLink(String title,
	 * String parentId, String link, String documentType, String documentTypeId) {
	 * DocumentItem item =
	 * DocumentItem.builder().documentType(documentType).documentTypeId(
	 * documentTypeId)
	 * 
	 * .externalParentId(parentId).name(title).title(title).build();
	 * 
	 * return documentItemRepository.save(item);
	 * 
	 * }
	 * 
	 * @Transactional public DocumentItem
	 * createFile(CreateMultipartDocumentItemRequest request) {
	 * log.info("Request - {}", (request.getParentId() == null));
	 * 
	 * 
	 * String fileNameOriginal = request.getFile().getOriginalFilename();
	 * 
	 * 
	 * log.info("fileNameOriginal - {}", fileNameOriginal);
	 * 
	 * String extension =
	 * fileNameOriginal.substring(fileNameOriginal.lastIndexOf('.') + 1);
	 * log.info("extension - {}", extension);
	 * 
	 * CreateFileEvent event = null;
	 * 
	 * if ("null".equals(request.getParentId()) || request.getParentId() == null) {
	 * // change this check
	 * 
	 * log.info("Parent id -null");
	 * 
	 * event =
	 * CreateFileEvent.builder().name(fileNameOriginal).externalParentId(Option.none
	 * ()) .file(request.getFile())
	 * .documentSource(request.getDocumentSource()).documentSourceId(request.
	 * getDocumentSourceId()) .build();
	 * 
	 * this.publish(event);
	 * 
	 * } else {
	 * 
	 * log.info("Parent id - {}", request.getParentId());
	 * 
	 * DocumentItem parent =
	 * this.documentItemRepository.findById(request.getParentId()).get(); // handle
	 * error // parent folder log.info("Parent id - {}", parent); // not found
	 * 
	 * event = CreateFileEvent.builder().name(fileNameOriginal)
	 * .file(request.getFile())
	 * .externalParentId(Option.of(parent.getExternalParentId()))
	 * .documentSource(request.getDocumentSource()).documentSourceId(request.
	 * getDocumentSourceId()) .build();
	 * 
	 * this.publish(event);
	 * 
	 * }
	 * 
	 * 
	 * log.info("Event - {}", event);
	 * 
	 * DocumentItem item =
	 * DocumentItem.builder().documentSource(event.getDocumentSource())
	 * .parentName(request.getParentName()).documentSourceId(event.
	 * getDocumentSourceId()).documentType("file") .extension(extension)
	 * .externalId(event.getExternalId()).externalParentId(event.getExternalParentId
	 * ().getOrNull())
	 * .externalParentName(event.getExternalParentName().getOrNull()).name(
	 * fileNameOriginal).build();
	 * 
	 * return documentItemRepository.save(item);
	 * 
	 * }
	 * 
	 */
}
