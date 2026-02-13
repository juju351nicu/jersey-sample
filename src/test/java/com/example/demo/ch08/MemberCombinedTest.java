package com.example.demo.ch08;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

/**
 * リスト8.17 2つのパラメータの組み合わせテスト
 * 
 * @author shuji.w6e
 */
class MemberCombinedTest {

	@ParameterizedTest
	@EnumSource(value = Gender.class, names = { "MALE", "FEMALE" })
	void canEntry(Gender gender) throws Exception {
		int[] AGES = { 15, 20, 25, 30 };
		for (int age : AGES) {
			System.out.println("canEntry(" + age + ", " + gender + ")");
		}
	}
}
