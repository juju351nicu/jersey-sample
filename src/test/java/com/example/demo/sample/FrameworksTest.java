package com.example.demo.sample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import com.example.demo.sample.model.ApplicationServer;
import com.example.demo.sample.model.Database;

/**
 * このような組み合わせが多数となる場合、パラメータ化テストを行うと、テストコードの可読性を損なう事なくテストの網羅性を高める事ができる。</br>
 * 組み合わせ表などはコードではわかりにくい為、外部ファイルを定義するとメンテナンスしやすくなる。</br>
 * 下記では、アプリケーションサーバとデータベースをそれぞれ列挙型としてDataPointsに定義しています。</br>
 * 列挙型の定義値をすべて指定するには、列挙型の全定義値を返すvaluesメソッドが便利です。</br>
 * DataPointsアノテーションに、それぞれの列挙型の定義値が定義されているため、2つのテストメソッドはすべての列挙型の定義値の組み合わせを引数として実行されます。</br>
 * それぞれのテストメソッドでは、パラメータの組み合わせをassumeTrueメソッドでフィルタリングする。</br>
 * この時、各テストで有効なパラメータかどうかを判定するために、外部ファイルに定義したサポート状況を参照し、boolean型を返すメソッドを組み込みました。</br>
 * 外部定義ファイルは組み合わせ表とほぼ同等のテキストファイルである為、読みやすくサポート状況の仕様に変更があった場合でも修正は簡単です。</br>
 * また修正時にはテストコードは修正する必要がありません。</br>
 * また、テストメソッドではどのパラメータによるテストケースであるかを明確にするため、パラメータの情報をアサーションのエラーメッセージに追加しています。</br>
 */
class FrameworksTest {

	public static ApplicationServer[] APP_SERVER_PARAMS = ApplicationServer.values();
	public static Database[] DATABASE_PARAMS = Database.values();
	static Map<String, Boolean> SUPPORTS = new HashMap<>();

	@BeforeEach
	void setUpClass() throws Exception {
		final File file = new File("../jersey-sample/src/test/resources/static/txt/support.txt");
		FileInputStream input = new FileInputStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		try {
			for (;;) {
				String line = reader.readLine();
				if (line == null)
					break;
				String[] params = line.split("\\|");
				SUPPORTS.put(params[0] + "-" + params[1], Boolean.valueOf(params[2]));
			}
		} finally {
			if (reader != null)
				reader.close();
		}
	}

	@ParameterizedTest
	@EnumSource(ApplicationServer.class)
	@EnumSource(Database.class)
	@DisplayName("isSupportは、表にて◯の組み合わせの場合にtrueを返す")
	void isSupportはtrueを返す(ApplicationServer appServer, Database db) throws Exception {
		assertTrue(isSupport(appServer, db));
		String desc = ", AppServer:" + appServer + ", DB:" + db;
		assertEquals(Frameworks.isSupport(appServer, db), true, desc);
	}

	@DisplayName("isSupportは、表にて×の組み合わせの場合にfalseを返す")
	@EnumSource(ApplicationServer.class)
	@EnumSource(Database.class)
	void isSupportはfalseを返す(ApplicationServer appServer, Database db) throws Exception {
		assertTrue(!isSupport(appServer, db));
		String desc = ", AppServer:" + appServer + ", DB:" + db;
		assertEquals(Frameworks.isSupport(appServer, db), false, desc);
	}

	private boolean isSupport(ApplicationServer appServer, Database db) {
		return SUPPORTS.get(appServer.toString() + "-" + db.toString());
	}
}
