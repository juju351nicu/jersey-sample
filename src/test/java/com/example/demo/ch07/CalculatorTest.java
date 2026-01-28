package com.example.demo.ch07;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * リスト7.1 単純なフィクスチャのセットアップ
 * 
 * @author shuji.w6e
 */
class CalculatorTest {

	@Test
	void multiplyで3と4の乗算結果が取得できること() throws Exception {
		Calculator calc = new Calculator();
		int expected = 12;
		int actual = calc.multiply(3, 4);
		assertEquals(actual, expected);
	}
}
