package com.cheetahapps.sales.document;


import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
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
	public DocumentItem createFolder(@Valid @RequestBody CreateFolderRequest request, @AuthenticationPrincipal Jwt jwt) {
		log.info("Request - {}", request);
		String tenantCode = jwt.getClaimAsString("tenantCode"); // get it from request context or create argument handler
		request.setRoot(tenantCode);
		return documentItemBusinessDelegate.createFolder(request);
	}
	
	@PostMapping("/files")
	public DocumentItem createFile(CreateFileRequest request) {
		log.info("request -> " + request);
		
		//return documentItemBusinessDelegate.createFile(request);
		
		return null;
		
	}
	
	/*
	@PostMapping("/link")
	public DocumentItem createLink(@Valid @RequestBody CreateDocumentItemRequest request) {
		
		return documentItemBusinessDelegate.createFolder(request.getParentId(),request.getFolder(), request.getDocumentSource(), 
				request.getDocumentSourceId(), request.getDocumentType(), request.getDocumentTypeId());
		
	}*/
	
	
}