package com.cheetahapps.sales.integration.box;


import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxTransactionalAPIConnection;
import com.cheetahapps.sales.documents.CreateFileEvent;
import com.cheetahapps.sales.documents.CreateFolderEvent;

import io.vavr.control.Option;
import io.vavr.control.Try;
import jodd.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Component
public class BoxConnector {

	private final BoxSettingRepository boxSettingRepository;

	@EventListener
	public void createFolder(CreateFolderEvent event) {

		if (StringUtil.equals(event.getDocumentSource(), "Box")) {

			this.boxSettingRepository.findFirstByDeleted(false).peek(box -> {
				// use try of in case of missing parent or duplicate
				createFolder(box, event);
			}).onEmpty(() -> {
				log.info("Box not configured yet.");
			});
		} else {
			log.info("Not a Box event.");
		}

	}

	@EventListener
	public void createFile(CreateFileEvent event) {

		if (StringUtil.equals(event.getDocumentSource(), "Box")) {

			this.boxSettingRepository.findFirstByDeleted(false).peek(box -> {
				// use try of in case of missing parent or duplicate
				
				Try.run(() -> 
					createFile(box, event)
				)
				.onFailure(exception -> log.error("Error writing file to BOX ", exception)); 
			}).onEmpty(() -> {
				log.info("Box not configured yet.");
			});
		} else {
			log.info("Not a Box event.");
		}

	}

	private void createFile(BoxSetting box, CreateFileEvent event) {
		BoxTransactionalAPIConnection api = new BoxTransactionalAPIConnection(box.getAccessToken());
		
			event.getExternalParentId().onEmpty(() -> { //"root"
				log.info("Box root");
				BoxFolder parentFolder = BoxFolder.getRootFolder(api);
				
				
				BoxFile.Info newFileInfo = 
						
						Try.of(() -> {
						return 
						parentFolder
						.uploadFile(event.getFile().getInputStream(), event.getFile().getName());
						}).onFailure(e -> log.error("Error uploading to box root",e)).get();
			
	
				event.setExternalId(newFileInfo.getID());
				event.setExternalParentId(Option.none());
				event.setExternalParentName(Option.none());
				
			}).peek(pid -> {
				log.info("Box not root");
				BoxFolder parentFolder = new BoxFolder(api, pid);
	
				BoxFile.Info newFileInfo = 
						
						Try.of(() -> {
						return 
						parentFolder
						.uploadFile(event.getFile().getInputStream(), event.getFile().getName());
						}).onFailure(e -> log.error("Error uploading to box root",e)).get();
	
				event.setExternalId(newFileInfo.getID());
				event.setExternalParentId(Option.of(newFileInfo.getParent().getID()));
				event.setExternalParentName(Option.of(newFileInfo.getParent().getName()));
				
			});
		
		

	}

	private void createFolder(BoxSetting box, CreateFolderEvent event) {
		BoxTransactionalAPIConnection api = new BoxTransactionalAPIConnection(box.getAccessToken());

		event.getExternalParentId().onEmpty(() -> { // "root"
			log.info("Box root");
			BoxFolder parentFolder = BoxFolder.getRootFolder(api);
			BoxFolder.Info childFolder = parentFolder.createFolder(event.getName());

			event.setExternalId(childFolder.getID());
			event.setExternalParentId(Option.none());
			event.setExternalParentName(Option.none());

		}).peek(pid -> {
			log.info("Box not root");
			BoxFolder parentFolder = new BoxFolder(api, pid);

			BoxFolder.Info childFolder = parentFolder.createFolder(event.getName());

			event.setExternalId(childFolder.getID());
			event.setExternalParentId(Option.of(childFolder.getParent().getID()));
			event.setExternalParentName(Option.of(childFolder.getParent().getName()));

		});

	}
}
