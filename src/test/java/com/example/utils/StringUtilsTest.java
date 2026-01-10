package com.example.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 状態を持たず、同じ入力ならば常に同じ結果を返すメソッドはユーティリティメソッドとも呼ばれます。</br>
 * ユーティリティは最もユニットテストを行いやすいクラス設計の1つです。</br>
 * ユーティリティメソッドにはいくつかの引数が定義され、一定の戻り値を返します。</br>
 * 従ってテストケースの入力値、出力値（実測値）、期待値が明確です。</br>
 * この例では、テストメソッドごとにテストケースを定義した。</br>
 * テストケースを可能な限り独立したテストメソッドに定義することは良い習慣。</br>
 * しかしながら、テストケースごとにテストメソッドを分けた場合、重複の多い、大量のテストメソッドを持つテストコードとなりがちです。</br>
 * そのような場合パラメータ化テスト（第８章）を使いテストコードを整理する。
 */
class StringUtilsTest {

	/**
	 * 「aaa」を入力すると、「aaa」が取得できる。
	 */
	@Test
	@DisplayName("aaaの場合")
	void toSnakeCase() {
		assertEquals(StringUtils.toSnakeCase("aaa"), "aaa");
	}

	/**
	 * 「HelloWorld」を入力すると、「hello_world」が取得できる。
	 */
	@Test
	@DisplayName("HelloWorldの場合")
	void toSnakeCase2() {
		assertEquals(StringUtils.toSnakeCase("HelloWorld"), "hello_world");
	}

	/**
	 * 「practiceJunit」を入力すると、「practice_junit」が取得できる。
	 */
	@Test
	@DisplayName("practiceJunitの場合")
	void toSnakeCase3() {
		assertEquals(StringUtils.toSnakeCase("practiceJunit"), "practice_junit");
	}
}
