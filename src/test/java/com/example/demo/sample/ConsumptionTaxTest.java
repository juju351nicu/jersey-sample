package com.example.demo.sample;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * リスト19.14 フィクスチャを用いたパラメータ化テスト</br>
 * 多くの入力値と期待値のセットに対してユニットテストを行いたい場合、それらの値を1つのフィクスチャとしてパラメータ化テストを行うと効果的。</br>
 * テストを実行すれば、各Fixtureオブジェクトがテストメソッドに渡されます。</br>
 * このようにテストコードを記述すれば、簡単にテストパターンを追加できます。</br>
 * Fixtureクラスのコンストラクタを工夫することで、テストパラメータの表と同じように読む事ができる。</br>
 * ----------------------------------------------------- </br>
 * 税率••••••••••••••••入力値（価格）••••••••••••期待値••••••</br>
 * ----------------------------------------------------- </br>
 * ••5••••••••••••••••••••100•••••••••••••••••••105••••••</br>
 * ••5•••••••••••••••••••3000••••••••••••••••••3150••••••</br>
 * •10•••••••••••••••••••••50••••••••••••••••••••55••••••</br>
 * ••5•••••••••••••••••••••50••••••••••••••••••••52••••••</br>
 * ••3•••••••••••••••••••••50••••••••••••••••••••51••••••</br>
 * ----------------------------------------------------- </br>
 * なお、入力値と期待値が多い場合や複雑なオブジェクトを生成する必要がある場合は、YAMLなどのフォーマットを利用した外部ファイルに定義すると良い。</br>
 */
public class ConsumptionTaxTest {

	@ParameterizedTest
	@MethodSource("listMethodSource")
	@DisplayName("applyで消費税が加算された価格が取得できる")
	void test1(Fixture fixture) throws Exception {
		ConsumptionTax sut = new ConsumptionTax(fixture.taxRate);
		String desc = "when rate=" + fixture.taxRate + ", price=" + fixture.price;
		assertEquals(sut.apply(fixture.price), fixture.expected, desc);
	}

	static Fixture[] listMethodSource() {
		return new Fixture[] { new Fixture(5, 100, 105), new Fixture(5, 3000, 3150), new Fixture(10, 50, 55),
				new Fixture(5, 50, 52), new Fixture(3, 50, 51), };
	}
//	static Stream<Arguments> listMethodSource() {
//		return Stream.of(Arguments.of(new Fixture(5, 100, 105)), Arguments.of(new Fixture(5, 3000, 3150)),
//				Arguments.of(new Fixture(10, 50, 55)), Arguments.of(new Fixture(5, 50, 52)),
//				Arguments.of(new Fixture(3, 50, 51)));
//	}
}
