package com.example.demo.ch11.withinterface;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

/**
 * リスト11.10 インタフェースを使って処理を差し替えたユニットテスト
 * 
 * @author shuji.w6e
 */
class DelegateObjectExampleTest {

	@Test
	void doSomethingを実行するとdateに現在時刻が設定される() throws Exception {
		final Date current = new Date();
		DelegateObjectExample sut = new DelegateObjectExample();
		sut.dateFactory = new DateFactory() {
			@Override
			public Date newDate() {
				return current;
			}
		};
		sut.doSomething();
		assertEquals(sut.date, current);
	}

}
