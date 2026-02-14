package com.example.demo.ch09;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;

/**
 * リスト9.14 ログを出力するTestWatcher<br>
 * TestWatcherクラスはテストの実行を監視するルールです。<br>
 * テスト実行時の様々なタイミングで追加の処理を実行できます。<br>
 * TestWactherはインターフェースであり必要なメソッドをオーバライドして利用します。<br>
 * リスト9.14は各フェーズでログを出力するTestWatcherです。<br>
 * ユニットテストはログなどを記録する必要がある場合や、ユニットテストの失敗時に通知が欲しいような場合に有効なルールです。<br>
 * 
 * @author shuji.w6e
 */
class TestWatcherExampleTest {
	// 3. テストクラスに登録
	@RegisterExtension
	MyTestWatcher watcher = new MyTestWatcher();

	@Test
	void 成功するテスト() throws Exception {
	}

	@Disabled("動作確認のためにはコメントアウトしてください")
	@Test
	void 失敗するテスト() throws Exception {
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
