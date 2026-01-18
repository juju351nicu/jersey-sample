package com.example.demo.ch06;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * リスト6.7 構造化されたインスタンス化テスト
 * 
 * @author shuji.w6e
 */
class UserTest {
	@Nested
	class インスタンス化テスト {
		@Test
		void デフォルトコンストラクタ() throws Exception {
			User instance = new User();
			assertEquals(instance.getName(), "nobody");
			assertEquals(instance.isAdmin(), false);
		}
	}
}
