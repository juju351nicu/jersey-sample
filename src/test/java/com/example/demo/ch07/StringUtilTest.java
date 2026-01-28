package com.example.demo.ch07;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * リスト7.3 フィクスチャのインラインセットアップ
 * 
 * @author shuji.w6e
 */
class StringUtilTest {

	@Test
	void isEmptyOrNullは空文字列でtrueを返す() throws Exception {
		// SetUp
		String input = "";
		boolean expected = true;
		// Exercise
		boolean actual = StringUtil.isEmptyOrNull(input);
		// Verify
		assertEquals(actual, expected);
	}

	@Test
	void isEmptyOrNullはAAAでfalseを返す() throws Exception {
		// SetUp
		String input = "AAA";
		boolean expected = false;
		// Exercise
		boolean actual = StringUtil.isEmptyOrNull(input);
		// Verify
		assertEquals(actual, expected);
	}
}
