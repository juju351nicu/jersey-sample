package com.example.demo.ch09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TemporaryFolderExample {

	public static void mkDefaultFiles(Path folder) throws IOException {
		Path tempFile = folder.resolve("UnitTest");
		Files.writeString(tempFile, "Hello World");
		Path tempFile2 = folder.resolve("readme.txt");
		Files.writeString(tempFile2, "Hello World");
		System.out.println(folder.getFileName());
	}

}
