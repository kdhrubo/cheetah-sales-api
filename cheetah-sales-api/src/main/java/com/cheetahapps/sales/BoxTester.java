package com.cheetahapps.sales;

import java.io.FileReader;
import java.io.Reader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxConfig;
import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import com.box.sdk.BoxTransactionalAPIConnection;
import com.box.sdk.BoxUser;
import com.box.sdk.CreateUserParams;

import lombok.extern.slf4j.Slf4j;

//@Component
@Slf4j
public class BoxTester implements ApplicationRunner {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		/*
		log.info("## getting box connection");
		Reader reader = new FileReader(
				"/Users/dhrubo/git/cheetah-sales-api/cheetah-sales-api/src/main/resources/config.json");
		BoxConfig boxConfig = BoxConfig.readFrom(reader);

		BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection
				.getAppUserConnection("12826040749", boxConfig);
		
		
		
				//.getAppEnterpriseConnection("12826040749", boxConfig);

		BoxFolder rootFolder = BoxFolder.getRootFolder(api);
		
		
		BoxFolder.Info childFolderInfo = rootFolder.createFolder("sample-test");

		for (BoxItem.Info itemInfo : rootFolder) {
			System.out.format("[%s] %s\n", itemInfo.getID(), itemInfo.getName());
		}
		
		
		CreateUserParams params = new CreateUserParams();
		params.setSpaceAmount(1073741824); // 1 GB
		// This optional param can be used to store any id, like an email, of the user
		// for which the associated app user is being created.
		
		BoxUser.Info user = BoxUser.createAppUser(api, "test1", params);

		System.out.format("User created with name %s and id %s\n\n", "test1", user.getID());
		*/
		
		enterprise();
	}
	
	private void enterprise() throws Exception{
		Reader reader = new FileReader(
				"/Users/dhrubo/git/cheetah-sales-api/cheetah-sales-api/src/main/resources/config.json");
		BoxConfig boxConfig = BoxConfig.readFrom(reader);
		
		//BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(boxConfig);
		
		//BoxAPIConnection api = new BoxAPIConnection("ifTFsN58fvltgojjCj4S0hWJ330PzWof");
		
		BoxTransactionalAPIConnection api = new BoxTransactionalAPIConnection("ifTFsN58fvltgojjCj4S0hWJ330PzWof");
		
		BoxFolder rootFolder = BoxFolder.getRootFolder(api);
		
		log.info("Root folder -> {}", rootFolder.getInfo().getID());
		/*
		for (BoxItem.Info itemInfo : rootFolder) {
			System.out.format("[%s] %s\n", itemInfo.getID(), itemInfo.getName());
		}*/
		
		BoxFolder.Info testFolder = rootFolder.createFolder("test3");
		
		log.info("Root folder -> {}", testFolder.getName());
	}

}
