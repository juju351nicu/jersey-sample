package com.example.demo.ch08;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * リスト8.3 じゃんけんのテストコード 8.2 入力値と期待値のパラメータ化<br/>
 * テスト対象メソッドが複数のパラメータを引数として持ち、その組み合わせによって異なる結果を返す場合、検証には多くのテストデータが必要です。<br/>
 * しかしながら、テストデータごとにテストメソッドを定義すると、テストメソッドの数が膨大になります。<br/>
 * また、各テストケース間の違いは入力値と期待値であり、テストコードは冗長になるでしょう。<br/>
 * JankenTestクラスはじゃんけん判定メソッドのテストコードの一部です。<br/>
 * じゃんけんにはグーチョキパーの3つの手Jankenクラスがあり、それぞれの手の組み合わせが全テストケースです。<br/>
 * もし、全パターンをテストするならばテストケースは９パターンとなります。<br/>
 * このように、テストデータの組わせによりテストコードが冗長となる問題を解決するには、テストデータとテストメソッドを分割するパラメータ化テストが有効です。<br/>
 * Junitでは@ParameterizedTestを使用する。<br/>
 * 
 * @author shuji.w6e
 */
class JankenTest {

	private Janken sut;

	@BeforeEach
	void setUp() throws Exception {
		sut = new Janken();
	}

	@Test
	void グーとチョキなら勝利() throws Exception {
		assertEquals(sut.judge(Janken.Hand.GU, Janken.Hand.TYOKI), Janken.Result.WIN);
	}

	@Test
	void グーとパーなら敗北() throws Exception {
		assertEquals(sut.judge(Janken.Hand.GU, Janken.Hand.PA), Janken.Result.LOSE);
	}

	@Test
	void グーとグーなら引き分け() throws Exception {
		assertEquals(sut.judge(Janken.Hand.GU, Janken.Hand.GU), Janken.Result.DRAW);
	}

	@Test
	void チョキとパーなら勝利() throws Exception {
		assertEquals(sut.judge(Janken.Hand.TYOKI, Janken.Hand.PA), Janken.Result.WIN);
	}
	// 以下略
}
