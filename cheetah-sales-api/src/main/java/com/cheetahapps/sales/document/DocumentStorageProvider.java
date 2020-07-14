package com.cheetahapps.sales.document;

import java.io.InputStream;

public interface DocumentStorageProvider {

	void createRoot(DocumentRoot root);

	void createFolder(Folder folder);

	void createFile(File file);
	
	void refreshFile(File file);
	
	void deleteFile(File file);
	
	void deleteFolder(Folder folder);
	
	InputStream getFile(File file);

}