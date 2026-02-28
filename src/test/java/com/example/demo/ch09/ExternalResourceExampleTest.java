package com.example.demo.ch09;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * リスト9.5 ExternalResourceの利用例<br>
 * ExternalResourceクラスは、テストの実行前に必要なリソースを準備し、テストの実行後にリソースを解放するルールです。<br>
 * ExternalResourceクラスは抽象クラスであり、具体的に管理するリソースはサブクラスで定義します。<br>
 * データベース、ソケット、組込みサーバなどを扱うテストを行う時に便利なルールです。<br>
 * リスト9.5は、組込みサーバの起動と停止をおこうルールです。<br>
 * リスト9.5ではネストしたクラスとしてルールを定義していますが、独立したクラスとして定義すれば複数のテストクラスから再利用できます。<br>
 * ExternalResourceクラスでは、beforeメソッドとafterメソッドの2つのメソッドをオーバーライドします。<br>
 * beforeメソッドには外部リソースの初期化処理を、afterメソッドには外部リソースの解放処理を定義してください。<br>
 * 初期化処理と解放処理の実行コストが大きく、クラス単位で実行できれば良いならばい、後術のClassRuleアノテーションを使うこともできます。<br>
 * なお、前述のTempolaryFolderクラスは、このExternalResourceクラスのサブクラスです。<br>
 * ExternalResourceはBeforeEachCallbackとAfterEachCallbackに代替される。
 * 
 * @author shuji.w6e
 */
class ExternalResourceExampleTest implements BeforeEachCallback, AfterEachCallback {
	Server server;

	@Override
	public void beforeEach(ExtensionContext context) throws Exception {
		// before() の処理
		server = new Server(8080);
		server.start();
	}

	@Override
	public void afterEach(ExtensionContext context) throws Exception {
		// after() の処理
		server.shutdown();
	}

	@Test
	void testCase() throws Exception {
	}
}
