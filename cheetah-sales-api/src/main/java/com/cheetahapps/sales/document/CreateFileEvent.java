package com.cheetahapps.sales.document;

import org.springframework.web.multipart.MultipartFile;

import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateFileEvent {
	
	private String name;
	
	private String documentSourceId;
	private String documentSource;

	private String externalId;
	private Option<String> externalParentId;
	private Option<String> externalParentName;
	
	private MultipartFile file;
}
