package com.example.demo.ch09;

/**
 * テストの失敗を確認する場合は、sleepメソッドに100以上の値を設定してください
 * 
 * @author shuji.w6e
 */
public class TimeoutExample {
	public static void doLongTask() throws InterruptedException {
		Thread.sleep(50L);
	}
}