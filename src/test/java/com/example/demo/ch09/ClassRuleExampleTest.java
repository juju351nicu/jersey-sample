package com.example.demo.ch09;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * リスト9.21 ClassRuleアノテーションによるサーバの接続と切断<br>
 * ユニットテストであh、原則として、テストケースごとに事前処理や事後処理を行います。<br>
 * しかしながら、データベースサーバの起動/停止など、テストケースごとに処理を行うのではコストが大きすぎる場合もあります。<br>
 * このような場合、初期化処理や事後処理では、Before/Afterアノテーションの代わりにBeforeClass/AfterClassアノテーションを利用しました。<br>
 * ルールも同様に、Ruleアノテーションの代わりに、ClassRuleアノテーションを使うことで、テストクラスごとにルールを適用できます。<br>
 * ClassRuleアノテーションを使用してルールを定義する方法は、Ruleアノテーションを利用する場合とほとんど変わりません。<br>
 * TestRuleインタフェースを実装したオブジェクトをテストクラスのpublicフィールドとして定義します。<br>
 * ただし、ClassRuleの場合は、staticフィールドでなければなりません。<br>
 * リスト9.21では、全てのテストケースの実行前にサーバに接続し、全テストの終了後にサーバから切断しています。<br>
 * なお、ClassRuleを利用した場合、applyメソッドに渡されるStatementオブジェクトはそのテストクラスを実行するテストランナーオブジェクトです。<br>
 * 従って、evaluateメソッドを呼び出すことにより、テストクラスに定義されたテストが順次実行されます。<br>
 * また、Descriptionオブジェクトはテストクラスのメタ情報です。<br>
 * 
 * @author shuji.w6e
 */
class ClassRuleExampleTest implements BeforeEachCallback, AfterEachCallback {

	@Override
	public void beforeEach(ExtensionContext context) throws Exception {
		// before() の処理
		System.out.println("connect");
	}

	@Override
	public void afterEach(ExtensionContext context) throws Exception {
		// after() の処理
		System.out.println("disconnect");
	}

	@Test
	void test01() throws Exception {
		System.out.println("test01");
	}

	@Test
	void test02() throws Exception {
		System.out.println("test02");
	}
}
