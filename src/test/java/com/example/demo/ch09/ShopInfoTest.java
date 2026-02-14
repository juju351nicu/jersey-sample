package com.example.demo.ch09;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * リスト9.8 ErrorCollectorを使ったインスタンス化テスト
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
