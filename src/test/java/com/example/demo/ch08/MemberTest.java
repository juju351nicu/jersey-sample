package com.example.demo.ch08;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

/**
 * リスト8.19 assumeによるパラメータのフィルタリング リスト8.21 パラメータの情報を指定するアサーション
 * 
 * @author shuji.w6e
 */
class MemberTest {

	@ParameterizedTest
	@EnumSource(value = Gender.class, names = { "MALE", "FEMALE" })
	void canEntryは25歳以下の女性の場合にtrueを返す(Gender gender) throws Exception {
		int[] AGES = { 15, 20, 25, 30 };
		for (int age : AGES) {
			assumingThat(age <= 25 && gender == Gender.FEMALE, () -> {
				String msg = "test1 When age=" + age + ", gender=" + gender;
				System.out.println(msg);
				assertEquals(Member.canEntry(age, gender), true);
			});
		}
	}

	@ParameterizedTest
	@EnumSource(value = Gender.class, names = { "MALE", "FEMALE" })
	void canEntryは25歳以下の女性でない場合にfalseを返す(Gender gender) throws Exception {
		int[] AGES = { 15, 20, 25, 30 };
		for (int age : AGES) {
			assumingThat(25 < age || gender != Gender.FEMALE, () -> {
				String msg = "test2 When age=" + age + ", gender=" + gender;
				System.out.println(msg);
				assertEquals(Member.canEntry(age, gender), false);
			});
		}
	}
}
