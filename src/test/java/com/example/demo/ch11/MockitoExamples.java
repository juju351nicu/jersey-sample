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
 * ・スタブメソッドの戻り値<br>
 * mockメソッドで生成されたモックオブジェクトでは、全てのメソッドがnull（プリミティブ型の場合はデフォルト値）を返すスタブメソッドとして設定されています。（リスト11.23）<br>
 * MockitではモックオブジェクトとスタブオブジェクトにAPIとして明確な区別はありません。<br>
 * メソッド呼び出しの検証を行うならばモックオブジェクトであり、スタブメソッドのみを利用するならばスタブオブジェクトです。<br>
 * どちらもmockメソッドで作成します。<br>
 * ・スタブメソッドの定義<br>
 * モックオブジェクト（スタブオブジェクト）に定義されたスタブメソッドの戻り値を再定義するには、Mockitクラスのwhenメソッドとその戻り値の型であるOngoingStubbingクラスのthenReturnメソッドを使用します。<br>
 * 例えば、getメソッドに0を指定して呼び出すと文字列Helloを返したいならば、リスト11.24のようにスタブオブジェクトを設定します。<br>
 * whenメソッドの引数の部分では対象となるメソッド(get)を実行し、そのメソッドの戻り値を引数とします。<br>
 * whenメソッドはOngoingStubbingオブジェクトを返すので、thenReturnメソッドを連鎖して呼び出しています。<br>
 * この時、thenReturnメソッドには、スタブメソッドの戻り値(Hello)を指定しています。<br>
 * このコードは自然言語（英語）として「when mock.get(0) then return
 * "Hello"」(getメソッドが引数0を呼び出された時の戻り値をHelloとする)と読めるように設計されています。<br>
 * この処理が実行される時にはうっらがわで複雑な処理が行われていますが、Mockitの利用者は自然な記述方法でスタブで定義できます。<br>
 * ・例外を送出するスタブメソッド<br>
 * スタブメソッドに例外を送出させたい場合は、thenReturnの代わりにthenThrowメソッドを使用します。（リスト11.25）<br>
 * なお、メソッドが送出できないチェック例外は設定できません。<br>
 * ・voidを返すスタブメソッド<br>
 * 通常の戻り値がvoid型のスタブメソッドを再定義することはありません。<br>
 * なぜならば、戻り値がvoid型のメソッドは戻り値が一定であり、オブジェクトの状態を変化させるメソッドだからです。<br>
 * とはいえ、戻り値がvoid型のメソッドを呼び出した時に例外を送出させたい場合もあります。<br>
 * ところが、Java言語の制約からMockitクラスのwhenメソッドは利用できません。<br>
 * そこで、リスト11.26のようにdoThrowメソッドを使います。<br>
 * リスト11.26では、Listオブジェクトのclearメソッドが呼び出された時に例外を送出されています。<br>
 * ・任意の引数に対するスタブメソッド<br>
 * Mockitでモックのスタブメソッドを定義するときは、そのメソッドが呼び出される時の引数を同時に指定します。<br>
 * すなわち、指定したメソッドが引数で呼び出された時に指定した結果を返します。<br>
 * しかしながら、任意の引数に対して一定の戻り値を返したい場合や、引数として渡されるオブジェクトが予測できない場合もあります。<br>
 * このような場合は、リスト11.27のようにanyIntメソッドなどを利用します。<br>
 * anyIntメソッド以外にも、anyStringメソッドやanyBooleanメソッドなどが利用できます。メソッドの引数の型によって使い分けてください。<br>
 * ・スタブメソッドの検証<br>
 * Mockitでスタブオブジェクトを利用するならば、前節までで解説したように、スタブオブジェクトに対し、スタブメソッドを定義すれば十分です。<br>
 * モックオブジェクトのスタブメソッドがどのように呼び出されたかを検証するには、Mockitクラスのverifyメソッドを利用します。<br>
 * リスト11.28では、モックオブジェクトのclearメソッドが1回、引数にHelloを指定したaddメソッドが2回呼ばれ、引数にWorldを指定したaddメソッドが呼ばれなかったことを検証しています。<br>
 * verifyメソッド引数にはモックオブジェクトを指定します。<br>
 * 第2引数には検証するメソッドの期待される呼び出し回数を指定します。<br>
 * ここでtimesメソッドを使用すれば、実行回数を指定した検証を行う事ができます。<br>
 * また、neverメソッドを使用すれば、そのメソッドが検証されていないことを検証できます。<br>
 * 指定を省略した場合は、「times(1)」を指定した場合と同等です。<br>
 * kのほか、atLeastOneceメソッドで（最低でも1回）、atLeastメソッド（最低でもn回）、atMOstメソッド（最大でn回）などが提供されています。<br>
 * 最後に、verifyメソッドの戻り値に対し、検証対象のメソッドを引数に指定して呼び出します。<br>
 * このようにモックオブジェクトにverfiyメソッドを使用することで、スタブメソッドがどのように呼び出されたかについて、厳密に検証できます。<br>
 * しかしながら、モックオブジェクトの呼び出しを検証することは、テスト対象オブジェクトの内部実装につようく依存することに注意してください。<br>
 * ・部分的なモックオブジェクト<br>
 * Mockitのモックオブジェクトでは、指定した型を模したダミーオブジェクトです。<br>
 * このため、全てのスタブオブジェクトはデフォルトの戻り値としてnullやプリミティブ型の基本値を返します。<br>
 * しかしながら、一部のメソッドのみをスタブメソッドに置き換えたい状況もあります。<br>
 * mockitでは、実オブジェクトを利用し、部分的なモックオブジェクトを作成することができます。<br>
 * 部分的なモックオブジェクトを作成するには、リスト11.29のようにspyメソッドを利用します。<br>
 * この方法で作成されたオブジェクトは、スタブメソッドが定義されていない限り、リアルオブジェクトの対応おするメソッド呼び出します。<br>
 * 従って、getメソッドは「Hello」を返し、sizeメソッドは「100」を返します。<br>
 * なお、部分的なモックオブジェクトを作成するときい利用するメソッドの名前が「spy」である理由は、歴史的な経緯と後述のスパイオブジェクトが部分的なモックオブジェクトとして実現しているためです。<br>
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

	/**
	 * ・Mockitの後置記法と前置記法<br>
	 * Mockitでは、モックオブジェクトにスタブメソッドを定義するときに後置記法と前置記法を使用する事ができます。<br>
	 * 後置記法では次のようにwhenメソッドを利用し、モックオブジェクトでスタブメソッドを呼び出し、thenXXXメソッドで戻り値を定義します。<br>
	 * when(mock.get(0)).thenReturn("Hello");<br>
	 * when(mock.get(1)).thenThrow(new IndexOutOfBoundsException());<br>
	 * 前置記法では、次のようにはじめにdoXXXメソッドで記録する戻り値を定義します。<br>
	 * あとから、whenメソッドにモックオブジェクトを指定し、スタブメソッドを実行します。<br>
	 * doReturn("Hello").when(mock).get(0);<br>
	 * doThrow(new IndexOutOfBoundsException()).when(mock).get(1);<br>
	 * どちらも同じモックオブジェクトを定義していますが、戻り値がvoidのメソッドでは、後者の記法でしか定義できません。<br>
	 */
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
