package com.example.demo.ch09;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * リスト9.1 Ruleアノテーションによるルールの宣言 リスト9.13 Timeoutルールを使ったタイムアウトの設定
 * 
 * @author shuji.w6e
 */
@Timeout(value = 100, unit = TimeUnit.SECONDS) // クラス内のすべてのテストに5秒の制限
class TimeoutExampleTest {

	@Test
	void 長い時間がかかるかもしれないテスト() throws Exception {
		TimeoutExample.doLongTask();
	}
}
