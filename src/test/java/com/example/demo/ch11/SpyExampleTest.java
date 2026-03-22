package com.example.demo.ch11;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * ・スパイオブジェクト<br>
 * Mockitでは、実オブジェクトを監視するスパイオブジェクトを作成する事ができます。<br>
 * スパイオブジェクトはテスト対象オブジェクトがレガシーコードに依存している場合に有効です。<br>
 * ただし、スタブオブジェクトやモックオブジェクトと同様にfinalクラスのスパイオブジェクトは作成できません。<br>
 * スパイオブジェクトを利用するには、リスト11.30のようにMockitクラスのspyメソッドを利用してスパイオブジェクトを生成します。<br>
 * スパイオブジェクトには、モックオブジェクトと同様に、特定の戻り値を再定義できます。<br>
 * スタブメソッドが定義されていない場合は、実オブジェクトの対応するメソッドが実行されます。<br>
 * また、verifyメソッドを利用し、メソッドの呼び出し回数などを検証することができます。<br>
 * このように、スパイオブジェクトは実オブジェクトの一部に対しスタブメソッドを定義できます。<br>
 * なお、「when~thenReturn」形式はスパイオブジェクトでは利用できません。<br>
 * ・メソッド実行時のコールバック<br>
 * スタブメソッドを定義するとき、Answerクラスを使いメソッド実行時のコールバックを登録できます。<br>
 * リスト11.31,11.32では、ロガーを扱うメソッドの実行時にロガーオブジェクトのメソッドを監視しています。<br>
 * Answerクラスのanswerメソッドで引数として渡されるInvocationOnMockオブジェクトは、実オブジェクトのメソッドを表すオブジェクトです。<br>
 * 実オブジェクトでの本来の振る舞いをさせるには、callRealMethodメソッドを呼び出します。<br>
 * このコードでは、ロガーに出力する前にその文字列を監視し、検証用に用意したinfoLogオブジェクトに記録しています。<br>
 * なお、Answerクラスにようるコールバックはモックオブジェクトに対しても利用できます。<br>
 * リスト11.22 ロガーオブジェクトにログを記録していることを検証するテスト<br>
 * リスト11.30 スパイオブジェクトの作成<br>
 * リスト11.32 ロガーのスパイオブジェクトの利用
 * 
 * @author shuji.w6e
 */
class SpyExampleTest {

	@Test
	void SpyLoggerを利用したテスト() {
		SpyExample sut = new SpyExample();
		SpyLogger spy = new SpyLogger(sut.logger);
		sut.logger = spy;
		sut.doSomething();
		assertEquals(spy.log.toString(), "doSomething");
	}

	@Test
	void Mockitoのspyを使ったテスト() throws Exception {
		// SetUp
		SpyExample sut = new SpyExample();
		Logger spy = Mockito.spy(sut.logger);
		final StringBuilder infoLog = new StringBuilder();
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				infoLog.append(invocation.getArguments()[0]);
				invocation.callRealMethod();
				return null;
			}
		}).when(spy).info(anyString());
		sut.logger = spy;
		// Exercise
		sut.doSomething();
		// Verify
		assertEquals(infoLog.toString(), "doSomething");
	}
}
