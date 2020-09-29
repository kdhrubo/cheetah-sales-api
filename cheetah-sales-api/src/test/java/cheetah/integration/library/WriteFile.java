package cheetah.integration.library;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;

import net.minidev.json.JSONObject;

public class WriteFile {
	
//	public static void writeFile(LinkedHashMap arg) throws IOException
	public static void writeTextFile(String arg) throws IOException
	{
		System.out.println("Inside write file"+arg);
		//String jsonString = new JSONObject(arg).toString();
		
		FileWriter file;
		file = new FileWriter("./src/test/java/TestData/authkey.txt");
		//file.write(jsonString);
		file.write(arg);
		file.close();
		
		System.out.println("File created successfully");
	}
	

}
