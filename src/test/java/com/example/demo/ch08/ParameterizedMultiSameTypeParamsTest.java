package com.example.demo.ch08;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * リスト8.12 複数の引数を定義したパラメータ化テスト(型が同じ場合)
 * 
 * @author shuji.w6e
 */
class ParameterizedMultiSameTypeParamsTest {

	@ParameterizedTest
	@MethodSource("provideStringsForIsBlank")
	void テストメソッド(int x, int y) throws Exception {
		System.out.println("テストメソッド(" + x + ", " + y + ")");
		assertEquals(x + y, 7);
	}

	private static Stream<Arguments> provideStringsForIsBlank() {
		return Stream.of(Arguments.of(3, 4));
	}
}
