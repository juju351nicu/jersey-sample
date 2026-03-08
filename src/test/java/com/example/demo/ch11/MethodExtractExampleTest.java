package com.example.demo.ch11;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

/**
 * リスト11.4 newDateメソッドをオーバーライドしたテスト
 * 
 * @author shuji.w6e
 */
class MethodExtractExampleTest {

	@Test
	void doSomethingを実行するとdateに現在時刻が設定される() throws Exception {
		final Date current = new Date();
		MethodExtractExample sut = new MethodExtractExample() {
			@Override
			Date newDate() {
				return current;
			}
		};
		sut.doSomething();
		assertEquals(sut.date, current);
	}
}
