package com.example.demo.ch09;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * リスト9.11 例外の発生とメッセージを検証するテスト
 * 
 * @author shuji.w6e
 */
class ExpectedExceptionExampleTest {

	@Disabled("例外メッセージが一致しないため、テストは失敗する")
	@Test
	void 例外の発生とメッセージを検証するテスト() {
		// 移行手順: ExpectedException.none() を削除し、assertThrows に置き換えます。
		// HogeExceptionが発生することを確認し、そのインスタンスを取得
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			throw new IllegalArgumentException("argument is null.");
		});
		// メッセージの検証
		assertEquals("argument is null.", exception.getMessage());
	}
}
