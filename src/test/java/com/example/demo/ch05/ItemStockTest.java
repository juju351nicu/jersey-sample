package com.example.demo.ch05;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * リスト5.5 Enclosedで構造化されたテストクラス<br/>
 * 構造化されたテストを行う場合、Junit4ではEnclosedクラスが使用されていた。<br/>
 * Enclosedクラスは構造化されたテストクラスのテストを実行するテストランナーです。<br/>
 * JUnit5ではこのEnclosedクラスが無くなり、代わりに@Nestedアノテーションを内部テストクラスに設定することで同様の機能を提供する。<br/>
 * テストケースは＠Nestedしたクラスに定義する。<br/>
 * ネストしたテストクラスはいくつでも定義できるため、テストクラスのテストケースが増えてきた場合に、同じ特徴を持つテストケースをまとめ、構造化できます。<br/>
 * 下記は、ItemStockクラスのテストクラスです。<br/>
 * テストケースを「空の場合」と「商品Aを1件含む場合」の2つはコンテキストに分け、それぞれネストしたテストクラスを定義しています。<br/>
 * 各ネストしたテストクラスは標準的なテストクラスとして作成します。<br/>
 * 従って、共通の処理はsetupメソッドに抽出できます。<br/>
 * 同じ初期化処理を行うテストケースをまとめることができるため、可読性の高いテストコードを作成できるようになります。<br/>
 * Enclosedクラスによるテストの構造化はテストケースが増えた場合に大きな効果をもたらします。<br/>
 * 詳細は第６章で解説します。
 * 
 * @author shuji.w6e
 */
class ItemStockTest {
	@Nested
	@DisplayName("空の場合")
	class EmptyClass {
		ItemStock sut;

		@BeforeEach
		void setUp() throws Exception {
			sut = new ItemStock();
		}

		@Test
		@DisplayName("size_Aが0を返す")
		void test1() throws Exception {
			assertEquals(sut.size("A"), 0);
		}

		@Test
		@DisplayName("contains_Aはfalseを返す")
		void test2() throws Exception {
			assertFalse(sut.contains("A"));
		}
	}

	@Nested
	@DisplayName("商品Aを1件含む場合")
	class OneItemClass {
		ItemStock sut;

		@BeforeEach
		void setUp() throws Exception {
			sut = new ItemStock();
			sut.add("A", 1);
		}

		@Test
		@DisplayName("sizeが1を返す")
		void test1() throws Exception {
			assertEquals(sut.size("A"), 1);
		}

		@Test
		@DisplayName("contains_Aはtrueを返す")
		void test2() throws Exception {
			assertTrue(sut.contains("A"));
		}
	}
}
