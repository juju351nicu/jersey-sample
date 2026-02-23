package com.example.demo.ch09;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * リスト9.8 ErrorCollectorを使ったインスタンス化テスト<br>
 * ErrorCollectorクラスは、アサーションの失敗やエラーが発生した場合でも、テストを継続して実行することができる仕組みを提供します。<br>
 * テストが失敗したという情報は、ErrorCollectorオブジェクトに蓄積され、テストは最後まで実行した後に評価されます。<br>
 * リスト9.8はコンストラクタのテストです。<br>
 * オブジェクトに定義された各フィールドの初期値を検証するためのErrorCollectorを利用しています。<br>
 * 検証をErrorCollectorクラスのcheckThatメソッドを利用することで、検証中に期待しない結果となった場合でもテストは失敗とならず、最後まで検証が行われます。<br>
 * これにより、テストが失敗しても、期待する結果とならない全ての項目に関する情報をしることができます。<br>
 * なお、ErrorCollectorクラスは、Verifierクラスのサブクラスです。<br>
 * 
 * @author shuji.w6e
 */
class ShopInfoTest {

	@Nested
	class インスタンス化テスト {

		@Disabled("初期値が不正であるためテストは失敗する")
		@Test
		void デフォルトコンストラクタ() throws Exception {
			// Exercise
			ShopInfo instance = new ShopInfo();
			// すべてのアサーションが評価され、エラーが収集される
			// ErrorCollector
			assertAll("heading",
					// Verify
					() -> assertNotNull(instance, "1st failed"), () -> assertNull(instance.id, "2nd passed"),
					() -> assertEquals(instance.name, "", "3rd failed"),
					() -> assertEquals(instance.address, "", "4rd failed"),
					() -> assertNull(instance.url, "5rd failed"));
		}
	}

}
