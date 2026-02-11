package com.example.demo.ch08;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * リスト8.13 フィクスチャオブジェクトによるパラメータ化テスト
 * 
 * @author shuji.w6e
 */
class CalculatorDataPointTest {

	@ParameterizedTest
	@MethodSource("listMethodSource")
	void add(Fixture p) throws Exception {
		Calculator sut = new Calculator();
		assertEquals(sut.add(p.x, p.y), p.expected);
	}

	static Fixture[] listMethodSource() {
		return new Fixture[] { new Fixture(3, 4, 7), new Fixture(0, 5, 5), new Fixture(-3, 1, -2), };
	}

	static class Fixture {
		int x;
		int y;
		int expected;

		Fixture(int x, int y, int expected) {
			this.x = x;
			this.y = y;
			this.expected = expected;
		}
	}

}