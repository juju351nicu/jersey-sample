package com.example.demo.ch08;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EnclosedParameterizedTypeTest {
	@Nested
	class intのパラメータ化テスト {

		@ParameterizedTest
		@ValueSource(ints = { 3, 4 })
		void 引数がint型のテストメソッド(int param) throws Exception {
			System.out.println("引数がint型のテストメソッド(" + param + ")");
		}
	}

	@Nested
	class Stringのパラメータ化テスト {

		@ParameterizedTest
		@ValueSource(strings = { "Hello", "World" })
		void 引数がString型のテストメソッド(String param) throws Exception {
			System.out.println("引数がString型のテストメソッド(" + param + ")");
		}
	}
}
