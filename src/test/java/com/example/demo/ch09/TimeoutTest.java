package com.example.demo.ch09;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * リスト9.12 アノテーションを使ったタイムアウト
 * 
 * @author shuji.w6e
 */
class TimeoutTest {

	@Test
	@Timeout(value = 100L, unit = TimeUnit.MILLISECONDS) // このテストメソッドは500ミリ秒制限
	void アノテーションを使ったタイムアウト() throws Exception {
		TimeoutExample.doLongTask();
	}
}
