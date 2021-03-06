package com.cheetahapps.sales.document;

import java.io.InputStream;

import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cheetahapps.sales.core.Base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document("DocumentItem")
@Data
@TypeAlias("DocumentItem")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
class DocumentItem extends Base{
	
	private String name;
	private String container;
	private String path;
	private String extension; 
	private DocType type;
	private long size;
	private String contentType;
	
	@Transient
	private InputStream in;
	
}

enum DocType {
	FILE,FOLDER,LINK
}