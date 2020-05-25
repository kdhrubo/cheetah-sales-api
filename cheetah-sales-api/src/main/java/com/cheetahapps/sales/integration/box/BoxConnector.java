package com.cheetahapps.sales.integration.box;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.box.sdk.BoxFolder;
import com.box.sdk.BoxTransactionalAPIConnection;

@Component
public class BoxConnector {
	
	@EventListener
	public void createFolder(BoxCreateFolderEvent event) {
		BoxTransactionalAPIConnection api = new BoxTransactionalAPIConnection("");
		
		event.getParent().onEmpty(() -> {
			BoxFolder parentFolder = BoxFolder.getRootFolder(api);
			BoxFolder.Info childFolder = parentFolder.createFolder(event.getName());
			
			event.setId(childFolder.getID());
			
		}).peek(pid -> {
			BoxFolder parentFolder = new BoxFolder(api, pid);
			
			BoxFolder.Info childFolder = parentFolder.createFolder(event.getName());
			
			event.setId(childFolder.getID());
		});
		
		
	}
}
