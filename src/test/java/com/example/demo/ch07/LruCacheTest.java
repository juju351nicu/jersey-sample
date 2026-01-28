package com.example.demo.ch07;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * リスト7.4 ネストしたクラスごとに行われる暗黙的セットアップ
 * 
 * @author shuji.w6e
 */
class LruCacheTest {
	@Nested
	class AとBを追加している場合 {
		LruCache<String> sut;

		@BeforeEach
		public void setUp() throws Exception {
			sut = new LruCache<String>();
			sut.put("A", "valueA");
			sut.put("B", "valueB");
		}

		@Test
		void sizeは2() throws Exception {
			assertEquals(sut.size(), 2);
		}

		@Test
		void get_AでvalueAを返しkeysはBA() throws Exception {
			assertEquals(sut.get("A"), "valueA");
			assertEquals(sut.keys.size(), 2);
			assertEquals(sut.keys.get(0), "B");
			assertEquals(sut.keys.get(1), "A");
		}

		@Test
		void get_BでvalueBを返しkeysはAB() throws Exception {
			assertEquals(sut.get("B"), "valueB");
			assertEquals(sut.keys.size(), 2);
			assertEquals(sut.keys.get(0), "A");
			assertEquals(sut.keys.get(1), "B");
		}

		@Test
		void get_Cでnullを返しkeysはAB() throws Exception {
			assertNull(sut.get("C"));
			assertEquals(sut.keys.size(), 2);
			assertEquals(sut.keys.get(0), "A");
			assertEquals(sut.keys.get(1), "B");
		}

		@Test
		void put_Cでsizeは3_keysはABCとなる() throws Exception {
			// Set up
			String key = "C";
			String item = "valueC";
			// Exercise
			sut.put(key, item);
			// Verify
			assertEquals(sut.size(), 3);
			assertEquals(sut.keys.size(), 3);
			assertEquals(sut.keys.get(0), "A");
			assertEquals(sut.keys.get(1), "B");
			assertEquals(sut.keys.get(2), "C");
		}
	}
}
