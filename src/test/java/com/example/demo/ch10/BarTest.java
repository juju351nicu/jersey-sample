package com.example.demo.ch10;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * リスト10.4 テストクラスのカテゴリ指定
 * 
 * @author shuji.w6e
 */
@Tag("FastTests")
class BarTest {

	@Test
	void test01() throws Exception {
		System.out.println("BarTest#test01");
	}

	@Test
	void test02() throws Exception {
		System.out.println("BarTest#test02");
	}
}
