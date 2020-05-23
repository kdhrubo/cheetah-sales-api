package com.cheetahapps.sales;

import java.io.FileReader;
import java.io.Reader;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxConfig;
import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import com.box.sdk.BoxUser;
import com.box.sdk.CreateUserParams;

import lombok.extern.slf4j.Slf4j;

//@Component
@Slf4j
public class BoxTester implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
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
		
		/*
		CreateUserParams params = new CreateUserParams();
		params.setSpaceAmount(1073741824); // 1 GB
		// This optional param can be used to store any id, like an email, of the user
		// for which the associated app user is being created.
		
		BoxUser.Info user = BoxUser.createAppUser(api, "test1", params);

		System.out.format("User created with name %s and id %s\n\n", "test1", user.getID());
		*/
	}

}
/*
 * App Name d5 Client ID eav6ko628ukv8xekx5sye31qe5igvofz
 * name test1 and id 12826040749
 */