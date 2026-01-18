package com.example.demo.ch06;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * リスト6.2 構造化されたItemStockのテストクラス<br/>
 * Enclosedクラスでは、ネストしたクラスをテストクラスとして扱うことのできるテストランナーです。<br/>
 * Enclosedテストランナーを利用すれば、初期化処理が共通していつテストケースをネストしたクラスにまとめ、テストクラスを構造化できます。<br/>
 * テストクラスの構造化を行うためには、外側のテストクラスにテストランナーとして、Enclosedクラスを設定します。<br/>
 * この時、外側のクラス名は標準的なテストクラスの命名規則に従い「テスト対象クラス名+Test」とします。<br/>
 * ネストしたクラスの名前は、グループ化するテストケースの前提条件を表す名前とします。<br/>
 * 例えば、ネストしたクラスの名前を「初期状態の場合」とすれば、読みやすく理解しやすいコードになります。<br/>
 * 下記はItemStockのテストケースをネストしたクラスで構造化したテストクラスです。<br/>
 * テストケースは共通の初期化処理で分類することにより、「空の場合」と「商品Aを1件含む場合」の2つのテストクラスにグループ化されました。<br/>
 * それぞれのテストクラスは、共通の初期化処理やテストデータ、すなわち共通のテストのコンテキイストで構造化されています。<br/>
 * なお、ネストしたクラスに、さらにEnclosedテストランナーを指定することもできます。しかしながら、Enclosedテストランナーで３階層以上に構造化することは避けるべきです。<br/>
 * ３階層以上の構造化が必要であったり、コンテキストごとのテストケースが多すぎるのであれば、テスト対象クラスの責務が大きすぎないか再検討ください。<br/>
 * 
 * @author shuji.w6e
 */
class ItemStockTest {
	@Nested
	class 空の場合 {
		ItemStock sut;

		@BeforeEach
		public void setUp() throws Exception {
			sut = new ItemStockImpl();
		}

		@Test
		public void size_Aが0を返す() throws Exception {
			assertEquals(sut.size("A"), 0);
		}

		@Test
		public void contains_Aはfalseを返す() throws Exception {
			assertFalse(sut.contains("A"));
		}
	}

	@Nested
	class 商品Aを1件含む場合 {
		ItemStock sut;

		@BeforeEach
		public void setUp() throws Exception {
			sut = new ItemStockImpl();
			sut.add("A", 1);
		}

		@Test
		public void sizeが1を返す() throws Exception {
			assertEquals(sut.size("A"), 1);
		}

		@Test
		public void contains_Aはtrueを返す() throws Exception {
			assertTrue(sut.contains("A"));
		}
	}
}
