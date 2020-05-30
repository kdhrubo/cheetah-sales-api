package com.cheetahapps.sales.integration.box;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.box.sdk.BoxFolder;
import com.box.sdk.BoxTransactionalAPIConnection;
import com.cheetahapps.sales.documents.CreateFolderEvent;

import io.vavr.control.Option;
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
		}
		else {
			log.info("Not a Box event.");
		}

	}

	private void createFolder(BoxSetting box, CreateFolderEvent event) {
		BoxTransactionalAPIConnection api = new BoxTransactionalAPIConnection(box.getAccessToken());

		event.getExternalParentId().onEmpty(() -> { //"root"
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
