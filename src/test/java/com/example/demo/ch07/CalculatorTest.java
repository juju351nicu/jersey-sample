package com.example.demo.ch07;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * リスト7.1 単純なフィクスチャのセットアップ
 * ・インラインセットアップ</br>
 * インラインセットアップ(inline setup)とは、最も基本的なフィクスチャのセットアップパターンです。</br>
 * インラインセットアップでは、テストメソッドごとにフィクスチャのセットアップを行います。（StringUtilTestクラス）</br>
 * インラインセットアップの特徴は、テストメソッド内でテストコードが完結し、テストコードの見通しが良くなる事です。</br>
 * しかしながら、BookStoreTestクラスのようにフィクスチャのセットアップが複雑で長い場合、テストの実行や検証が相対的に短くなるのが欠点です。</br>
 * 一般的に長すぎるメソッドはコードの可読性を悪くします。</br>
 * 絶対的な基準はありませんが、筆者の主観では20行を超えるメソッドは長いと感じます。</br>
 * 非常に長いフィクスチャのセットアップが記述されたテストコードは可読性が悪く、どこで操作を行いどこで検証しているのかを理解できません。</br>
 * フィクスチャのセットアップが複雑であり、そのコードが長くなるようであれば、他のパターンの利用を検討ください。</br>
 * とはいえ、インラインセットアップはシンプルで理解しやすいフィクスチャのセットアップパターンです。</br>
 * 1つ目のテストメソッドを書く場合や、単純なフィクスチャの場合は、積極的に利用すべきです。</br>
 * 
 * @author shuji.w6e
 */
class CalculatorTest {

	@Test
	void multiplyで3と4の乗算結果が取得できること() throws Exception {
		Calculator calc = new Calculator();
		int expected = 12;
		int actual = calc.multiply(3, 4);
		assertEquals(actual, expected);
	}
}
