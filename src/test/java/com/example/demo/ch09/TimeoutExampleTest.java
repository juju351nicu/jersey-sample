package com.example.demo.ch09;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * リスト9.1 Ruleアノテーションによるルールの宣言<br>
 * ・タイムアウトを設定する<br>
 * Timeoutアノテーションはテストケースのタイムアウトを設定するアノテーションです。<br>
 * テストの実行時間が指定された値を超えると、そのテストは自動的に失敗となります。<br>
 * タイムアウトはJUnitの標準的な機能としても提供されています。<br>
 * リスト9.12のようにTestアノテーションのtimeoutにタイムアウト時間（単位はミリ秒）を設定できます。<br>
 * 一方、リスト9.13はTimeoutアノテーションを利用したコードです。<br>
 * どちらの記法であってもタイムアウトが設定できることは変わりありませんが、@Timeoutをクラスに利用した場合、テストクラス内の全てのテストメソッドにタイムアウトが設定できます。<br>
 * リスト9.13 Timeoutルールを使ったタイムアウトの設定<br>
 * 
 * @author shuji.w6e
 */
@Timeout(value = 100, unit = TimeUnit.MILLISECONDS) // クラス内のすべてのテストに5秒の制限
class TimeoutExampleTest {

	@Test
	void 長い時間がかかるかもしれないテスト() throws Exception {
		TimeoutExample.doLongTask();
	}
}
