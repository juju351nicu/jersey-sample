package com.example.demo.ch06;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * リスト6.6 構造化されたArrayListのテストクラス
 * 
 * @author shuji.w6e
 */
class ArrayListEnclosedTest {

	@Nested
	class listに1件追加してある場合 {
		private List<String> sut;

		@BeforeEach
		public void setUp() throws Exception {
			sut = new ArrayList<String>();
			sut.add("A");
		}

		@Test
		public void sizeは1を返す() throws Exception {
			int actual = sut.size();
			assertEquals(actual, 1);
		}
	}

	@Nested
	class listに2件追加してある場合 {
		private List<String> sut;

		@BeforeEach
		public void setUp() throws Exception {
			sut = new ArrayList<String>();
			sut.add("A");
			sut.add("B");
		}

		@Test
		void sizeは2を返す() throws Exception {
			int actual = sut.size();
			assertEquals(actual, 2);
		}
	}
}