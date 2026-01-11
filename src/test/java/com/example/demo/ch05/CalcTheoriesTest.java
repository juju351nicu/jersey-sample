package com.example.demo.ch05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * リスト5.6 ￼ Theoriesによるパラメータ化テスト
 * 
 * @author shuji.w6e
 */

class CalcTheoriesTest {

	@ParameterizedTest
	@MethodSource("listMethodSource")
	void add(int x, int y, int expected) throws Exception {
		Calc sut = new Calc();
		assertEquals(sut.add(x, y), expected);
	}

	/**
	 * @DataPoints public static int[][] VALUES
	 * @return
	 */
	static Stream<Arguments> listMethodSource() {
		return Stream.of(Arguments.of(0, 0, 0), Arguments.of(0, 1, 1), Arguments.of(1, 0, 1), Arguments.of(3, 4, 7));
	}
}
