package com.example.demo.ch08;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * リスト8.20 Windows環境のみで実行するテストケース
 * 
 * @author shuji.w6e
 */
class WindowsOnlyTest {

	@Test
	void windows環境では改行はrn() throws Exception {
		assertTrue(System.getProperty("os.name").contains("Windows"));
		assertEquals(System.getProperty("line.separator"), "\r\n");
	}
}