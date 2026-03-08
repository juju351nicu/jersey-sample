package com.example.demo.ch11;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * リスト11.2 システム時間に依存する処理を含むメソッドのテスト
 * 
 * @author shuji.w6e
 */
class DateDependencyExampleTest {

	@Disabled
	@Test
	void doSomethingでdateに現在時刻が設定される() throws Exception {
		DateDependencyExample sut = new DateDependencyExample();
		sut.doSomething();
		// このアサーションは実行タイミングによって成功にも失敗にもなる
		assertEquals(sut.date, new Date());
	}

}
