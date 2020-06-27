package com.cheetahapps.sales.document;


import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.cheetahapps.sales.core.AbstractController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/docs")
class DocumentItemController extends AbstractController<DocumentItem, String> {
	
	private final DocumentItemBusinessDelegate documentItemBusinessDelegate;

	public DocumentItemController(DocumentItemBusinessDelegate documentItemBusinessDelegate) {
		super(documentItemBusinessDelegate);
		this.documentItemBusinessDelegate = documentItemBusinessDelegate;
	}
	
	@GetMapping("/q")
	public List<DocumentItem> search(@RequestParam("rsql") String rsql) {
		log.info("RSQL - {}", rsql);
		return documentItemBusinessDelegate.searchAll(rsql);
	}
	
	@PostMapping("/folders")
	public DocumentItem createFolder(@Valid @RequestBody CreateDocumentItemRequest request) {
		
		return documentItemBusinessDelegate.createFolder(request);
		
	}
	
	@PostMapping("/files")
	public DocumentItem createFile(CreateMultipartDocumentItemRequest request) {
		log.info("request -> " + request);
		
		return documentItemBusinessDelegate.createFile(request);
		
		
	}
	
	/*
	@PostMapping("/link")
	public DocumentItem createLink(@Valid @RequestBody CreateDocumentItemRequest request) {
		
		return documentItemBusinessDelegate.createFolder(request.getParentId(),request.getFolder(), request.getDocumentSource(), 
				request.getDocumentSourceId(), request.getDocumentType(), request.getDocumentTypeId());
		
	}*/
	
	
}