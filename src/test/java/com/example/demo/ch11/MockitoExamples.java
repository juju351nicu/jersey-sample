package com.example.demo.ch11;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * 11.3 Mockitによるモックオブジェクト<br>
 * Junitではスタブやモックといったテストダブルを扱う昨日は提供していません。<br>
 * このため、スタブやモックを使ってテストケースを作成するときは外部ライブラリを利用します。<br>
 * 前節で書いたように、単純なスタブならば独自に実装することも簡単です。<br>
 * しかし、モックオブジェクトを作成して各メソッドの呼び出し回数の検証などを行うのは大きな手間なので、ライブラリを導入します。<br>
 * テストダブルを扱うライブラリは一般的にモックライブラリとして公開されています。<br>
 * なぜならば、モック機能はスタブ機能のほぼ上位互換となるためです。<br>
 * 目的や使い方によってそのオブジェクトがモックなのかスタブなのかという違いはありますが、APIとしては同じであるライブラリがほとんどです。<br>
 * なお、スパイはライブラリによっては対応していません。<br>
 * ・Mockitとは？<br>
 * Javaのモック用ライブラリとしてはMockit、EasyMock、jMockなどがよく知られています。<br>
 * 本書ではMockitを紹介します。<br>
 * Mockitを利用することで、簡単にスタブオブジェクトを作成し、スタブオブジェクトのメソッドが呼び出されたときに期待される振る舞いをさせる事ができます。<br>
 * また、モックオブジェクトによるメソッド呼び出しの検証や、実オブジェクトをラップしたスパイオブジェクトによる監視も行う事ができます。<br>
 * リスト11.23 モックオブジェクトに定義されたメソッドの戻り値<br>
 * リスト11.24 スタブメソッドの設定<br>
 * リスト11.25 例外を送出するスタブメソッド<br>
 * リスト11.26 void型を返すスタブメソッド<br>
 * リスト11.27 任意の整数に対するスタブメソッド<br>
 * リスト11.28 スタブメソッドの検証<br>
 * リスト11.29 部分的なモックオブジェクト<br>
 * 
 * @author shuji.w6e
 */
@ExtendWith(MockitoExtension.class) // JUnit5とMockitoを連携 [8]
class MockitoExamples {

	// モックオブジェクトの作成
	@Mock
	List<String> mockList;

	// スタブオブジェクトの作成
	@Mock
	List<String> stub;

	@Test
	void モックオブジェクトに定義されたメソッドの戻り値() throws Exception {
		assertNull(mockList.get(0));
		assertFalse(mockList.add("Hello"));
	}

	@Test
	void スタブメソッドの設定() throws Exception {
		when(stub.get(0)).thenReturn("Hello"); // スタブの設定
		assertEquals(stub.get(0), "Hello"); // 検証
	}

	@Test
	void 例外を送出するスタブメソッド() throws Exception {
		when(stub.get(0)).thenReturn("Hello");
		when(stub.get(1)).thenReturn("World");
		when(stub.get(2)).thenThrow(new IndexOutOfBoundsException());
		assertThrows(IndexOutOfBoundsException.class, () -> {
			stub.get(2); // 例外が送出される
		});
	}

	@Test
	void 戻り値がvoid型のメソッド() throws Exception {
		doThrow(new RuntimeException()).when(stub).clear();
		assertThrows(RuntimeException.class, () -> {
			stub.clear(); // 例外が送出される
		});
	}

	@Test
	void 任意の整数に対するスタブメソッド() throws Exception {
		when(stub.get(anyInt())).thenReturn("Hello");
		assertEquals(stub.get(0), "Hello");
		assertEquals(stub.get(1), "Hello");
		assertEquals(stub.get(999), "Hello");
	}

	@Test
	void モックの検証() throws Exception {
		mockList.clear();
		mockList.add("Hello");
		mockList.add("Hello");
		verify(mockList).clear();
		verify(mockList, times(2)).add("Hello");
		verify(mockList, never()).add("World");
	}

	@Test
	void 部分的なモックオブジェクト() throws Exception {
		List<String> list = new LinkedList<>();
		List<String> spy = spy(list);
		doReturn("Mockito").when(spy).get(1);
		spy.add("Hello");
		spy.add("World");
		verify(spy).add("Hello");
		verify(spy).add("World");
		assertEquals(spy.get(0), "Hello");
		assertEquals(spy.get(1), "Mockito");
	}

}
