package com.cheetahapps.sales.document.s3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
@ConditionalOnProperty(
		value="app.document.storage.provider",
		havingValue = "s3",
		matchIfMissing = false
)
public class S3Configuration {
	
	@Value("${app.aws.s3.accessKeyId}")
	private String accessKey;
	@Value("${app.aws.s3.accessKeySecret}")
	private String secretKey;
	
	@Bean
	public AmazonS3 s3client() {
		
		AWSCredentials credentials = new BasicAWSCredentials(
				accessKey, 
				secretKey
				);
		
		return AmazonS3ClientBuilder
		  .standard()
		  .withCredentials(new AWSStaticCredentialsProvider(credentials))
		  .withRegion(Regions.US_EAST_2)
		  .build();
	}
	
	@Bean
	public S3StorageProvider storageProvider() {
		return new S3StorageProvider(s3client());
	}
}
