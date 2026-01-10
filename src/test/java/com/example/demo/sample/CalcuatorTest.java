package com.example.demo.sample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 例外の送出を検証するテストケースでは、Testアノテーションのexpected属性を使用する。</br>
 * 標準的なテストメソッドでは結果の検証にアサーションを使用しますが、例外の送出を検証するテストケースでは検証部分をアノテーションで宣言的に記述する。</br>
 * expected属性をもとにフレームワークにより検出される。</br>
 * なお、例外メッセージ、エラーコード、ネストされた例外などがある場合は、ExpectedExceptionルールを使用する。(Junit4の場合)</br>
 * Jnit5からはassertThrowsが推奨される。
 */
class CalcuatorTest {

	/** エラーメッセージ */
	private final static String errorMessage = "y is zero.";

	/**
	 * 正常終了の場合
	 */
	@Test
	@DisplayName("正常系の場合")
	void testNormal() {
		Calcuator sut = new Calcuator();
		// 例外がthrowされるため代入されることはない
		int expected = 2;
		int actual = sut.divide(4, 2);
		assertEquals(expected, actual);
	}

	/**
	 * 第２引数に0を指定してdivideを呼び出すと、IllegalArgumentExceptionが発生する
	 */
	@Test
	@DisplayName("第2引数に0を指定すると例外が発生する場合")
	void testExceptionThrown() {
		Calcuator sut = new Calcuator();
		// 例外が発生することを期待する
		assertThrows(IllegalArgumentException.class, () -> sut.divide(1, 0));
	}

	/**
	 * 第２引数に0を指定してdivideを呼び出すと、エラーメッセージが発生する。
	 */
	@Test
	@DisplayName("エラーメッセージの確認")
	void testExceptionWithSpecificMessage() {
		Calcuator sut = new Calcuator();
		// 例外のメッセージも検証する場合
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			// 例外を発生させるコード
			sut.divide(1, 0);
		});
		assertEquals(errorMessage, exception.getMessage());
	}
}
