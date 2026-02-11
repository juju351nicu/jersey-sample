package com.example.demo.ch08;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.Test;

/**
 * リスト8.18 assumeの利用
 * 
 * @author shuji.w6e
 */
class AssmeTest {

	@Test
	void assume() throws Exception {
        assumingThat(1 == 0, () -> {
            System.out.println("This only runs if ENV is 'CI'.");
        });
		fail("この行は実行されない");
	}
}