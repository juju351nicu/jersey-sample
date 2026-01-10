package com.example.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 同値クラスのテスト</br>
 * ユニットテストにおいて、テストデータの選択は重要。</br>
 * 効率よく、少ないテストデータを実装できれば、実装コストだけでなく、メンテナンスコストも減らせる。</br>
 * この問題のテスト対象メソッドでは、入力値は無数に存在するが、期待される結果はtrueとfalseの２種類しかない。</br>
 * 期待される結果に着目することで、効率よくテストデータを選択できる。</br>
 * もちろん10と7以外の入力値によるテストを行う事もできるが、バグを検知できる可能性は低い。</br>
 * 他の値を入れても、10と7のような処理が行われ、同じ結果を繰り返す。これらのグループを同値クラスという。</br>
 * 完璧なソフトウェアを作る事は不可能です。</br>
 * 同値クラスに着目することで、コストと品質とのバランスを保つことができる。
 */
class NumberUtilsTest {

	@Test
	@DisplayName("入力値に「10」を与えると、trueを返す。")
	void normalTrue() {
		assertEquals(NumberUtils.isEven(10), true);
	}

	@Test
	@DisplayName("入力値に「7」を与えると、falseを返す。")
	void normalFalse() {
		assertEquals(NumberUtils.isEven(7), false);
	}
}
