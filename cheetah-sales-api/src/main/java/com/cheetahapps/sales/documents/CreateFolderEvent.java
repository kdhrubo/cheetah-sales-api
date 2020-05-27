package com.cheetahapps.sales.documents;

import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateFolderEvent {

	private Option<String> parent;
	private String folder;
	
	private String documentSourceId;
	private String documentSource;

	private String externalId;

	private String externalParentId;

	private String externalParentName;
}
