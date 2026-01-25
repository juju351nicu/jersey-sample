package com.example.demo.ch06;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * リスト6.5 構造化されていないArrayListのテストクラス ・共通の状態に着目する<br/>
 * テスト対象クラスが状態を持つ場合、事前処理として行われるテスト対象オブジェクトへの操作が重要です。<br/>
 * テスト対象オブジェクトは、事前処理の操作の結果、特定の状態となります。<br/>
 * このようにテスト対象オブジェクトの状態に着目し、テストケースをグループ化するパターンも有効です。<br/>
 * 例えば、ArrayListなどのコンテナクラスのユニットテストでは、コンテナが空の状態、コンテナに要素が1件ある状態、2件ある状態などで、それぞれメソッドの振舞いを検証します。<br/>
 * コンテナを初期化した後、addメソッドを1回呼び出せば、リストに要素が1件ある状態になります。<br/>
 * しかしながら、このような操作がテストメソッド内で定義されていると、テスト対象の操作が曖昧になる危険性があります。下記。<br/>
 * 一方、ArrayListEnclosedTestクラスはテスト対象オブジェクトの状態に着目し、それぞれの状態でテストクラスを定義したテストコードです。<br/>
 * 
 * @author shuji.w6e
 */
class ArrayListFlatTest {

	private List<String> sut;

	@BeforeEach
	void setUp() throws Exception {
		sut = new ArrayList<>();
	}

	@Test
	void listに1件追加してある場合_sizeは1を返す() throws Exception {
		sut.add("A");
		int actual = sut.size();
		assertEquals(actual, 1);
	}

	@Test
	void listに2件追加してある場合_sizeは2を返す() throws Exception {
		sut.add("A");
		sut.add("B");
		int actual = sut.size();
		assertEquals(actual, 2);
	}
}
