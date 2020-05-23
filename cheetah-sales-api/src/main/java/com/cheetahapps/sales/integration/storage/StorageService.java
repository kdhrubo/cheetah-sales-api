package com.cheetahapps.sales.integration.storage;

import java.io.InputStream;

public interface StorageService {

	
	void createFolder(String parent, String folder);
	
	void createFile(String folder, InputStream is, String fileName);
	
	void createRoot(String rootName);
}
