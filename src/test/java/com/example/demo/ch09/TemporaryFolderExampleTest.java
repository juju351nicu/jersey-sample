package com.example.demo.ch09;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * リスト9.3 TemporaryFolderの使用<br>
 * ・一時ファイルを使う<br>
 * TempDirアノテーションは、テストの実行ごとにテンポラリフォルダを作成し、テストの実行後に作成したテンポラリフォルダを削除するルールです。<br>
 * また、@TempDirを使うことにより、テンポラリフォルダでのファイルやフォルダの作成が簡単に行えます。<br>
 * TempDirアノテーションは、ファイルシステムを扱うメソッドのユニットテストで便利なツールです。<br>
 * ファイルシステムに関連するユニットテストでは、テストの独立性を高めるため、テストケースごとに独立したフォルダを利用したり、使用したフォルダをテストの後処理でクリアしたりします。<br>
 * それらの処理は初期化処理や後処理で記述できますが、このTempDirアノテーションを使用すればそれらの処理を省略できます。<br>
 * テストの初期化処理の前に、そのテスト専用のテンポラリフォルダが作成され、テスト終了時に破棄されます。<br>
 * リスト9.3の例では、TempDirアノテーションを使用し、テンポラリフォルダを使ったテストを行っています。<br>
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
