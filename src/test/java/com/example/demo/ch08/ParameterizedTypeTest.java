package com.example.demo.ch08;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * リスト8.9 複数のテストメソッドが定義されたパラメータ化テスト(わかりにくい)
 * 
 * @author shuji.w6e
 */

class ParameterizedTypeTest {

	@ParameterizedTest
	@ValueSource(ints = { 3, 4 })
	void 引数がint型のテストメソッド(int param) throws Exception {
		System.out.println("引数がint型のテストメソッド(" + param + ")");
	}

	@ParameterizedTest
	@ValueSource(strings = { "Hello", "World" })
	void 引数がString型のテストメソッド(String param) throws Exception {
		System.out.println("引数がString型のテストメソッド(" + param + ")");
	}
}
