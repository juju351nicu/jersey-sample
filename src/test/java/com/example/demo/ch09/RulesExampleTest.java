package com.example.demo.ch09;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * リスト9.2 複数のルールの宣言
 * 
 * @author shuji.w6e
 */
class RulesExampleTest {

	@Test
	void test(TestInfo testInfo) throws Exception {
		// 例外が発生することを確認
		// ExpectedException.none()はJunit5では下記の書き方になる。
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			throw new IllegalArgumentException("不正な引数です");
		});
		// 発生した例外のメッセージを検証
		assertEquals("不正な引数です", exception.getMessage());
		// テストメソッド名を取得
		assertEquals(testInfo.getTestMethod().get().getName(), "test");
	}
}
