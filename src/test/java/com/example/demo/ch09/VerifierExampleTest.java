package com.example.demo.ch09;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;

/**
 * リスト9.6 Verifierによる事後検証<br>
 * Verifierクラスは、テストの最終局面で共通した検証を行いたい場合に使用するルールです。<br>
 * つまり、テストの事後条件を検証します。<br>
 * ExternalResourceクラスと同様にVerifierクラスは抽象クラスです。<br>
 * 要件に応じてverifyメソッドをオーバーライドして使用します。<br>
 * 事後条件の検証は後処理メソッドで行うこともできますが、事後条件の検証が複雑であったり、複数のテストクラスで横断的に行いたい場合は、ルールとして独立したクラスに抽出すると便利です。<br>
 * リスト9.6では、Verifierルールを利用してテスト対象オブジェクトに対する事後条件を検証しています。<br>
 * この検証は、Afterアノテーションの付与された後処理のさらに後に実行されます。<br>
 * つまり、リスト9.6では、次の順番で実行されます。<br>
 * 
 * @author shuji.w6e JUnit 5でVerifierを実装する方法 (推奨)<br>
 *         JUnit5では、AfterEachCallbackインタフェースを使用して、各テストの終了後に検証を行う独自の拡張機能（Extension）を作成するのが最も一般的で強力な方法です。<br>
 * 
 */
class VerifierExampleTest {
	@RegisterExtension
	MyCustomVerifier verifier = new MyCustomVerifier();

	@Test
	void testMethod() {
		// テストの最後で状態を更新
		verifier.setVerified(true);
	}

	@AfterEach
	void tearDown() {
		System.out.println("@AfterEach");
	}
}

//検証ロジックを定義するクラス
class MyCustomVerifier implements AfterEachCallback {
	private boolean verified = false;

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	@Override
	public void afterEach(ExtensionContext context) throws Exception {
		// テスト終了後にチェック
		System.out.println("テスト終了後にチェック");
		assertTrue(verified, "テスト後にチェックが実施されませんでした");
	}
}