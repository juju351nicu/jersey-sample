package com.example.demo.ch09;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * リスト9.15 テスト名の取得
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
