package com.example.demo.ch08;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * リスト8.18 assumeの利用
 * 
 * @author shuji.w6e
 */
class AssmeTest {

	@Test
	void assume() throws Exception {
		assertEquals(1, 0);
		fail("この行は実行されない");
	}
}