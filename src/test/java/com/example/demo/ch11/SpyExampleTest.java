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
 * リスト11.22 ロガーオブジェクトにログを記録していることを検証するテスト リスト11.30 スパイオブジェクトの作成 リスト11.32
 * ロガーのスパイオブジェクトの利用
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
