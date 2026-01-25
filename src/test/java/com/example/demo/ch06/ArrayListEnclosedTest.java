package com.example.demo.ch06;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * リスト6.6 構造化されたArrayListのテストクラス<br/>
 * 下記のように、テストの事前条件を満たすためのコードをsetUpメソッドに移動すれば、テストメソッドが読みやすくなります。<br/>
 * なお、この例では、構造化しなかった場合（ArrayListFlatTestクラス）よりも、した場合（ArrayListEnclosedTestクラス）の方がテストコードが長くなってしまいましたが、テストケースが増えてくれば、構造化されたテストクラスの方が短くて読みやすいコードとなるでしょう。<br/>
 * 
 * @author shuji.w6e
 */
class ArrayListEnclosedTest {

	@Nested
	class listに1件追加してある場合 {
		private List<String> sut;

		@BeforeEach
		void setUp() throws Exception {
			sut = new ArrayList<String>();
			sut.add("A");
		}

		@Test
		void sizeは1を返す() throws Exception {
			int actual = sut.size();
			assertEquals(actual, 1);
		}
	}

	@Nested
	class listに2件追加してある場合 {
		private List<String> sut;

		@BeforeEach
		void setUp() throws Exception {
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