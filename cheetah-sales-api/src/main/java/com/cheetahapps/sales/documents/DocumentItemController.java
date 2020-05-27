package com.cheetahapps.sales.documents;


import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public Page<DocumentItem> search(@RequestParam("rsql") String rsql, Pageable pageable) {
		return documentItemBusinessDelegate.search(rsql, pageable);
	}
	
	@PostMapping("/folders")
	public DocumentItem createFolder(@Valid @RequestBody CreateDocumentItemRequest request) {
		
		return documentItemBusinessDelegate.createFolder(request.getParentId(),request.getFolder(), request.getDocumentSource(), 
				request.getDocumentSourceId(), request.getDocumentType(), request.getDocumentTypeId());
		
	}
	
	@PostMapping("/link")
	public DocumentItem createLink(@Valid @RequestBody CreateDocumentItemRequest request) {
		
		return documentItemBusinessDelegate.createFolder(request.getParentId(),request.getFolder(), request.getDocumentSource(), 
				request.getDocumentSourceId(), request.getDocumentType(), request.getDocumentTypeId());
		
	}
	
	
}