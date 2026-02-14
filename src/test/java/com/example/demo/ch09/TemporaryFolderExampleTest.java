package com.example.demo.ch09;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * リスト9.3 TemporaryFolderの使用
 * 
 * @author shuji.w6e
 */
class TemporaryFolderExampleTest {

	@Test
	void mkDefaultFilesで2つのファイルが作成される(@TempDir Path tempFolder) throws Exception {
		TemporaryFolderExample.mkDefaultFiles(tempFolder);
		File folder = tempFolder.toFile();
		String[] actualFiles = folder.list();
		Arrays.sort(actualFiles);
		assertEquals(actualFiles.length, 2);
		assertEquals(actualFiles[0], "UnitTest");
		assertEquals(actualFiles[1], "readme.txt");
	}

}
