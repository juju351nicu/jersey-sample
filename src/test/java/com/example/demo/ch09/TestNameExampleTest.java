package com.example.demo.ch09;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * リスト9.15 テスト名の取得<br>
 * Junit4のTestNameクラスはJunit5ではTestInfoクラスが使用されている。<br>
 * TestInfoクラスはテストメソッド内でテストメソッド名を取得できる。<br>
 * リスト9.15では、TestInfoオブジェクトからテストメソッド名を取得している。<br>
 * Junitによるユニットテストでは、テストメソッド名をテストケースの概要とします。<br>
 * このため、テストメソッド名は有益な情報を多く含んでおり、テストメソッド内で利用したいシーンは少なくありません。<br>
 * テストメソッド内で利用したいシーンは少なくありません。<br>
 * テストメソッド名と外部リソースファイルの名前を関連付けることもあるでしょう。<br>
 * しかしながら、Javaで実行中のメソッド名を取得するのは簡単ではありません。※1<br>
 * TestInfoクラスを利用することで、簡単に実行中のテストメソッド名を取得できます。<br>
 * ※1: StackTraceオブジェクトを使うことで、TestInfoを使わずにメソッド名を取得することは可能です。<br>
 * 
 * @author shuji.w6e
 */
class TestNameExampleTest {

	@Test
	@DisplayName("テスト名の取得")
	void テスト名(TestInfo testInfo) throws Exception {
		// テストメソッド名を取得
		assertEquals(testInfo.getTestMethod().get().getName(), "テスト名");
		// ディスプレイ名（@DisplayNameで設定したもの）を取得可能
		assertEquals(testInfo.getDisplayName(), "テスト名の取得");
	}

}
