package com.example.demo.ch08;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

/**
 * リスト8.17 2つのパラメータの組み合わせテスト<br>
 * ・8.3組み合わせテスト<br>
 * 下記のクラスでは、会員サイトへのエントリが可能かどうかを判定するメソッドに対して、年齢と性別の2つの引数を持つテストメソッドを定義しています。<br>
 * このテストを実行すると、年齢と性別の全組み合わせ、すなわち「15歳男性、20歳男性、25歳男性、30歳男性、15歳女性、20歳女性、25歳女性、30歳女性」の８パターン全てが出力されます。<br>
 * しかし、これだけではテスト結果が同一のパラメータしか定義できないため、あまり使い道がありません。<br>
 * そこで後術のassumingThatメソッドと組み合わせて利用する。
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
