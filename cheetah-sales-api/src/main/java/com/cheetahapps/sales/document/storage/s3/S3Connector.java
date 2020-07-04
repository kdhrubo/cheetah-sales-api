package com.cheetahapps.sales.document.storage.s3;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.cheetahapps.sales.document.CreateFolderEvent;
import com.cheetahapps.sales.document.CreateRootEvent;

import jodd.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class S3Connector {
	
	private final AmazonS3 s3client;
	
	@EventListener
	public void createBucketHandler(CreateRootEvent event) {
		log.info("Creating root");
		if(s3client.doesBucketExistV2(event.getName())) {
		    log.info("Bucket - {} already exists.", event.getName());
		    
		}
		else {
			log.info("Creating bucket - {}", event.getName());
			s3client.createBucket(event.getName());	
		}
	}
	
	@EventListener
	public void createFolderHandler(CreateFolderEvent event) {
		log.info("Create folder event - {}", event);
		String folderName;
		if(StringUtil.equals(event.getContainer(), "/")) {
			folderName = event.getName() + "/";
		}
		else {
			String container = StringUtil.replaceFirst(event.getContainer(), "/", "");
			folderName = container + "/" + event.getName() + "/";
		}
		
		
		
		log.info("Folder - {}", folderName);
		
		// create meta-data for your folder and set content-length to 0
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);
		// create empty content
		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
		// create a PutObjectRequest passing the folder name suffixed by /
		PutObjectRequest putObjectRequest = new PutObjectRequest(event.getRoot(),
				folderName, emptyContent, metadata);
		// send request to S3 to create folder
		s3client.putObject(putObjectRequest);

	}
}
