package com.example.demo.ch05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * リスト5.6 ￼ Theoriesによるパラメータ化テスト<br/>
 * JUnit4では、Theoriesクラスを使いテストクラス単位でパラメータ化テストできましたが、JUnit５から、テストメソッド単位でできるようになりました。<br/>
 * テストメソッドに@ParameterizedTestを記載することで、パラメータ化テストをサポートする。<br/>
 * パラメータ化テストは、テストケースとテストデータを分離し、同じテストメソッドを複数のパラメータで再利用して使うテクニックです。<br/>
 * JUnitでは、@ParameterizedTestを指定することで、パラメータを持つテストメソッドを定義できるようにないます。<br/>
 * この時＠Testの代わりに@ParameterizedTestを使用します。<br/>
 * テストメソッドに渡されるパラメータは@MethodSource,@ValueSourceなどに定義します。<br/>
 * 下記は、パラメータとしてint型の配列を持つテストメソッドを定義しています。<br/>
 * テストメソッドでは、配列の0番目と1番目をテスト対象メソッドの入力値とし、2番目の値を期待値として検証を行っています。<br/>
 * このように、複数の異なる入力値に対する検証を行いたい場合に効率よく実装できます。<br/>
 * 入力値や期待値を外部ファイルとして定義することもできる。<br/>
 * パラメータ化テストは第8章にて詳細に扱います。<br/>
 * 
 * @author shuji.w6e
 */
class CalcTheoriesTest {

	@ParameterizedTest
	@MethodSource("listMethodSource")
	void add(int x, int y, int expected) throws Exception {
		Calc sut = new Calc();
		assertEquals(sut.add(x, y), expected);
	}

	/**
	 * @DataPoints public static int[][] VALUES
	 * @return
	 */
	static Stream<Arguments> listMethodSource() {
		return Stream.of(Arguments.of(0, 0, 0), Arguments.of(0, 1, 1), Arguments.of(1, 0, 1), Arguments.of(3, 4, 7));
	}
}
