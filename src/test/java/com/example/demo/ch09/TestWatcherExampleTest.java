package com.example.demo.ch09;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;

/**
 * リスト9.14 ログを出力するTestWatcher
 * 
 * @author shuji.w6e
 */
class TestWatcherExampleTest {
	// 3. テストクラスに登録
	@RegisterExtension
	MyTestWatcher watcher = new MyTestWatcher();

	@Test
	public void 成功するテスト() throws Exception {
	}

	@Disabled("動作確認のためにはコメントアウトしてください")
	@Test
	public void 失敗するテスト() throws Exception {
		fail("NG");
	}

	// 1. & 2. TestWatcherインターフェースの実装
	class MyTestWatcher implements TestWatcher {
		@Override
		public void testSuccessful(ExtensionContext context) {
			System.out.println("Success: " + context.getDisplayName());
		}

		@Override
		public void testFailed(ExtensionContext context, Throwable cause) {
			System.out.println("Failed: " + context.getDisplayName() + ", Reason: " + cause.getMessage());
		}
	}
}
