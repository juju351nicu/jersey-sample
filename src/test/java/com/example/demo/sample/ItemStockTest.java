package com.example.demo.sample;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * 戻り値がvoid型であるメソッドは戻り値による検証ができない。</br>
 * この為、状態の変化を計測するためのメソッドやフィールドを参照することで検証を行う必要がる。</br>
 * 下記では、数量を取得できるgetNumメソッドを用いてaddメソッドの検証を行っています。</br>
 * 同様にgetNumメソッドもaddメソッドを使わなければ十分な検証を行うことができません。</br>
 * このようにいくつかのメソッドが相互作用するクラスでは、テストも複雑になる。</br>
 * addメソッドのテストでは、ItemStockクラスのvaluesフィールドをパッケージプライベートなどで定義し、valuesフィールドを直接検証することでも実現できる。</br>
 * しかし、この方法ではプロダクションコードの実装に強く依存し、リファクタリングを行いにくくなる。</br>
 * なお、いくつかのメソッドのテストが相互依存している場合、テストの成否が他のテストの結果に影響し、テスト全体の信頼性を保つ事が困難。
 * 下記の例であれば、getNumメソッドのテストが失敗すると関連するテストは全て期待された動きをしない可能性がある。</br>
 * 下記の例はあまり良いテストではない。
 */

class ItemStockTest {

	@Nested
	class 初期状態の場合 {
		ItemStock sut;
		Item book;

		@BeforeEach
		void setup() {
			book = new Item("book", 3000);
			sut = new ItemStock();
		}

		@Test
		@DisplayName("初期状態で、getNumで0が取得できる。")
		void getNumはbookで0を返す() throws Exception {
			assertEquals(sut.getNum(book), 0);
		}

		@Test
		@DisplayName("初期状態で、addでItemを追加するとgetNumで1が取得できる。")
		void addでbookを追加するとgetNumで1を返す() throws Exception {
			sut.add(book);
			assertEquals(sut.getNum(book), 1);
		}
	}

	@Nested
	class bookが1回追加されている場合 {
		ItemStock sut;
		Item book;

		@BeforeEach
		void setup() {
			book = new Item("book", 3800);
			sut = new ItemStock();
			sut.add(book);
		}

		@Test
		@DisplayName("Itemが1つ追加されている状態で、getNumで1が取得できる。")
		void getNumはbookで1を返す() throws Exception {
			assertEquals(sut.getNum(book), 1);
		}

		@Test
		@DisplayName("Itemが1つ追加されている状態で、addで同じItemオブジェクトを追加するとgetNumで2が取得できる。")
		void addでbookを追加するとgetNumで2を返す() throws Exception {
			sut.add(book);
			assertEquals(sut.getNum(book), 2);
		}

		@Test
		@DisplayName("Itemが1つ追加されている状態で、addで同じItemオブジェクトを追加するとgetNumで1が取得できる。")
		void addでbikeを追加するとgetNumでbikeは1を返す() throws Exception {
			Item bike = new Item("bike", 57000);
			sut.add(bike);
			assertEquals(sut.getNum(book), 1);
			assertEquals(sut.getNum(bike), 1);
		}
	}
}
