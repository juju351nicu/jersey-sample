package com.example.demo.sample;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * リスト19.11 境界値のテスト リスト19.13 境界値テストの構造化とパラメータ化</br>
 * このような範囲などを判定するメソッドのテストでは、境界値に対するテストが有効です。</br>
 * 境界値に対するテストは、プログラムのバグが判定の境界値に偏在する性質を利用し境界値をテストデータとして選択するテスト技法です。</br>
 * Rangeオブジェクトを２種類用意し、それぞれの両端を基準に4つずつのテストデータを選択しています。</br>
 * 下記ではRangeオブジェクトを２種類用意し、それぞれの範囲の両端を基準に4つずつのテストデータを選択しています。</br>
 * Rangeオブジェクトでは、条件やテストデータがそれほど多くないため、下記のように1つのテストメソッドに連続してテストコードを記述しても可読性は低くなりません。</br>
 * しかしながら、条件が複雑になりテストデータも多くなった場合はパラメータ化テストを検討してください。</br>
 * 下記はパラメータ化テストとEnclosedによるコンテキストごとにテストケースを整理したテストコードです。</br>
 * Enclosedテストランナーはネストさせる事ができるため、パラメータとそのテストの期待値ごとにコンテキストを分けることもできる。</br>
 * このテストコードは複雑に感じますが、テストケースが構造化されているため、テストデータの追加や変更は行いやすいでしょう。</br>
 */
public class RangeTest {

	@Test
	void containsのテスト() {
		// min=0.0,max=10.5のRangeの場合
		assertEquals(new Range(0d, 10.5).contains(-0.1), false);
		assertEquals(new Range(0d, 10.5).contains(0.0), true);
		assertEquals(new Range(0d, 10.5).contains(10.5), true);
		assertEquals(new Range(0d, 10.5).contains(10.6), false);
		// min=-5.1,max=5.1のRangeの場合
		assertEquals(new Range(-5.1, 5.1).contains(-5.2), false);
		assertEquals(new Range(-5.1, 5.1).contains(-5.1), true);
		assertEquals(new Range(-5.1, 5.1).contains(5.1), true);
		assertEquals(new Range(-5.1, 5.1).contains(5.2), false);
	}

	@Nested
	class Rangeが0から10_5で {
		@Nested
		static class かつ範囲外の場合 {
			Range sut;

			@BeforeEach
			void setUp() {
				sut = new Range(0d, 10.5);
			}

			@ParameterizedTest
			@ValueSource(doubles = { -0.1, 10.6 })
			void containsはfalseを返す(double value) throws Exception {
				assertEquals(sut.contains(value), false);
			}
		}

		@Nested
		class かつ範囲内の場合 {
			Range sut;

			@BeforeEach
			void setUp() {
				sut = new Range(0d, 10.5);
			}

			@ParameterizedTest
			@ValueSource(doubles = { 0, 10.5 })
			void containsはtrueを返す(double value) throws Exception {
				assertEquals(sut.contains(value), true);
			}
		}
	}

}
