package com.cheetahapps.sales;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class WriteFile {

	public static void writeFile(String arg) throws FileNotFoundException
	{
		PrintWriter writter= new PrintWriter("AuthKey.json");
		writter.write(arg);
	}
}
