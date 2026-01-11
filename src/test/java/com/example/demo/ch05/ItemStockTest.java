package com.example.demo.ch05;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * リスト5.5 Enclosedで構造化されたテストクラス
 * 
 * @author shuji.w6e
 */
class ItemStockTest {
	@Nested
	class 空の場合 {
		ItemStock sut;

		@BeforeEach
		void setUp() throws Exception {
			sut = new ItemStock();
		}

		@Test
		void size_Aが0を返す() throws Exception {
			assertEquals(sut.size("A"), 0);
		}

		@Test
		void contains_Aはfalseを返す() throws Exception {
			assertFalse(sut.contains("A"));
		}
	}

	@Nested
	class 商品Aを1件含む場合 {
		ItemStock sut;

		@BeforeEach
		void setUp() throws Exception {
			sut = new ItemStock();
			sut.add("A", 1);
		}

		@Test
		void sizeが1を返す() throws Exception {
			assertEquals(sut.size("A"), 1);
		}

		@Test
		void contains_Aはtrueを返す() throws Exception {
			assertTrue(sut.contains("A"));
		}
	}
}
