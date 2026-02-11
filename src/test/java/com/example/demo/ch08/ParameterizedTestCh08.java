package com.example.demo.ch08;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * リスト8.8 @DataPointによるパラメータの指定
 * 
 * @author shuji.w6e
 */
class ParameterizedTestCh08 {

	ParameterizedTestCh08() {
		System.out.println("初期化");
	}

	@ParameterizedTest
	@ValueSource(ints = { 3, 4 })
	void 引数を持つテストメソッド(int param) throws Exception {
		System.out.println("引数を持つテストメソッド(" + param + ")");
	}
}