package com.cheetahapps.sales.document.s3;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.springframework.util.Assert;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.cheetahapps.sales.document.File;
import com.cheetahapps.sales.document.Folder;
import com.cheetahapps.sales.document.DocumentRoot;
import com.cheetahapps.sales.document.DocumentStorageProvider;

import jodd.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class S3StorageProvider implements DocumentStorageProvider {

	private final AmazonS3 s3client;

	@Override
	public void createRoot(DocumentRoot root) {
		
		log.info("Creating root");
		if (s3client.doesBucketExistV2(root.getName())) {
			log.info("Bucket - {} already exists.", root.getName());

		} else {
			log.info("Creating bucket - {}", root.getName());
			s3client.createBucket(root.getName());
		}

	}

	@Override
	public void createFolder(Folder folder) {
		log.info("Create folder - {}", folder);
		String folderName;
		if (StringUtil.equals(folder.getContainer(), "/")) {
			folderName = folder.getName() + "/";
		} else {
			String container = StringUtil.replaceFirst(folder.getContainer(), "/", "");
			folderName = container + "/" + folder.getName() + "/";
		}

		log.info("Folder - {}", folderName);

		// create meta-data for your folder and set content-length to 0
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);
		// create empty content
		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
		// create a PutObjectRequest passing the folder name suffixed by /
		PutObjectRequest putObjectRequest = new PutObjectRequest(folder.getRoot(), folderName, emptyContent, metadata);
		// send request to S3 to create folder
		s3client.putObject(putObjectRequest);

	}

	@Override
	public void createFile(File file) {
		log.info("Create file  - {}", file);
		String container = file.getContainer();
		String path = StringUtil.replaceFirst(container, "/", "") + "/" + file.getName();

		if (StringUtil.equals(container, "/")) {
			path = file.getName();
		}

		log.info("path - {}", path);

		// create meta-data for your folder and set content-length to 0
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(file.getSize());

		// create a PutObjectRequest passing the folder name suffixed by /
		PutObjectRequest putObjectRequest = new PutObjectRequest(file.getRoot(), path, file.getInput(), metadata);
		// send request to S3 to create folder
		s3client.putObject(putObjectRequest);

	}

	@Override
	public void refreshFile(File file) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteFile(File file) {
		String container = file.getContainer();
		String path = StringUtil.replaceFirst(container, "/", "") + "/" + file.getName();

		if (StringUtil.equals(container, "/")) {
			path = file.getName();
		}

		log.info("path - {}", path);

		s3client.deleteObject(new DeleteObjectRequest(file.getRoot(), path));

	}

	@Override
	public void deleteFolder(Folder folder) {
		// TODO Auto-generated method stub

	}

	@Override
	public InputStream getFile(File file) {
		log.info("download file  - {}", file);
		String container = file.getContainer();
		String path = StringUtil.replaceFirst(container, "/", "") + "/" + file.getName();

		if (StringUtil.equals(container, "/")) {
			path = file.getName();
		}

		log.info("path - {}", path);
		
		S3Object fullObject = s3client.getObject(new GetObjectRequest(file.getRoot(), path));
		
		return fullObject.getObjectContent();
		
	}
}
