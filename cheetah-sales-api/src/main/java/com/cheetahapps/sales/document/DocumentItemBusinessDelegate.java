package com.cheetahapps.sales.document;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.vavr.control.Option;
import io.vavr.control.Try;
import jodd.util.StringUtil;

import com.cheetahapps.sales.core.AbstractBusinessDelegate;
import com.cheetahapps.sales.event.ProvisionTenantEvent;
import com.cheetahapps.sales.problem.DuplicateDataProblem;
import com.cheetahapps.sales.problem.NoDataFoundProblem;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
class DocumentItemBusinessDelegate extends AbstractBusinessDelegate<DocumentItem, String> {

	private DocumentItemRepository documentItemRepository;

	@Autowired
	private DocumentStorageProvider storageProvider;

	public DocumentItemBusinessDelegate(DocumentItemRepository documentItemRepository) {
		super(documentItemRepository);
		this.documentItemRepository = documentItemRepository;
	}

	@EventListener
	public void provision(ProvisionTenantEvent event) {
		if (!event.isExistingTenant()) {
			storageProvider.createRoot(DocumentRoot.of(event.getTenant().getCode()));
		}
	}

	@Transactional
	public DocumentItem createFolder(CreateFolderRequest request) {
		log.info("Container - {}", request.getContainer());
		String container = request.getContainer();
		String path = container + "/" + request.getName();

		if (StringUtil.equals(container, "/")) {
			path = container + request.getName();
		}

		// check if folder exists
		Option<DocumentItem> folder = this.documentItemRepository.findByPath(path);

		if (folder.isEmpty()) {
			// create folder in this container / parent folder

			storageProvider.createFolder(Folder.of(request.getName(), request.getContainer(), request.getRoot()));

			DocumentItem item = DocumentItem.builder().name(request.getName()).container(container).type(DocType.FOLDER)
					.path(path).build();

			return documentItemRepository.save(item);
		} else {
			throw new DuplicateDataProblem("Folder already exists");
		}

	}

	@Transactional
	public DocumentItem createFile(CreateFileRequest request) {
		String path = request.getContainer() + "/" + request.getFile().getOriginalFilename();

		if (StringUtil.equals(request.getContainer(), "/")) {
			path = request.getContainer() + request.getFile().getOriginalFilename();
		}

		// check if folder exists
		Option<DocumentItem> file = this.documentItemRepository.findByPath(path);

		if (file.isEmpty()) {
			// change on error this must error out.
			Try.run(() ->

			storageProvider.createFile(File.of(request.getContainer(), request.getFile().getOriginalFilename(),
					request.getRoot(), request.getFile().getSize(), request.getFile().getInputStream()))

			).onFailure(ex -> log.info("Error - {}", ex.getMessage()))
					.onSuccess(t -> log.info("File saved in storage"));

			String extension = request.getFile().getOriginalFilename()
					.substring(request.getFile().getOriginalFilename().lastIndexOf('.') + 1);

			DocumentItem item = DocumentItem.builder().name(request.getFile().getOriginalFilename())
					.container(request.getContainer()).type(DocType.FILE).path(path).extension(extension)
					.size(request.getFile().getSize()).contentType(request.getFile().getContentType()).build();


			// update size of container recursively in an event listener
			/*
			Option<DocumentItem> containterItem = this.documentItemRepository.findByPath(request.getContainer());

			if (!containterItem.isEmpty()) {
				DocumentItem cItem = containterItem.get();
				cItem.setSize(cItem.getSize() + item.getSize());

				documentItemRepository.save(cItem);

			}*/

			return documentItemRepository.save(item);

		} else {
			throw new DuplicateDataProblem("File already exists");
		}
	}

	@Transactional
	public DocumentItem getFile(String id, String root) {
		// check if file exists
		Optional<DocumentItem> file = this.documentItemRepository.findById(id);

		if (file.isPresent()) {
			DocumentItem item = file.get();

			InputStream in = storageProvider.getFile(File.of(item.getContainer(), item.getName(), root, 0L, null));
			item.setIn(in);
			return item;
		} else {
			throw new NoDataFoundProblem("Document not found.");
		}

	}

	@Transactional
	public void delete(String id, String root) {
		// check if file exists
		Optional<DocumentItem> file = this.documentItemRepository.findById(id);

		if (file.isPresent()) {
			DocumentItem item = file.get();

			storageProvider.deleteFile(File.of(item.getContainer(), item.getName(), root, 0L, null));
			
			this.documentItemRepository.deleteById(item.getId());
			
		} else {
			throw new NoDataFoundProblem("Document not found.");
		}

	}

	@Transactional(readOnly = true)
	public List<DocumentItem> searchAll(String rsql) {
		log.info("rsql - {}", rsql);
		return documentItemRepository.searchAll(rsql, DocumentItem.class);
	}

}
