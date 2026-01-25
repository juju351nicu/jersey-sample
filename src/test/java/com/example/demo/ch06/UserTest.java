package com.example.demo.ch06;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * リスト6.7 構造化されたインスタンス化テスト ・コンストラクタのテストは分ける<br/>
 * インスタンスかテスト、すなわちオブジェクトを生成するテストは、他のテストとは性質が異なります。<br/>
 * なぜならば、テスト対象オブジェクトの生成自体がテスト内容だからです。<br/>
 * インスタンス化テストは他のテストケースと分け、「初期状態を検証する特別なコンテキスト」と考えるとよいでしょう。<br/>
 * 下記はデフォルトコンストラクタを生成した際、初期値が期待する値と同じになっているかをテストしています。<br/>
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
