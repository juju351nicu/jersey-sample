package com.example.demo.ch09;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;

/**
 * リスト9.20 RuleChainによるルールの連鎖<br>
 * ルールを利用して外部リソースなどの制御を行っている時、特定の順序で制御させたいケースがあります。<br>
 * 例えば、アプリケーションサーバとデータベースサーバを利用するユニットテストがあり、アプリケーションサーバより前にデータベースサーバを起動しておかなければならない。といったケースです。<br>
 * それぞれの機能はカスタムルールとして簡単に定義できます。<br>
 * ところが、それらのカスタムルールをテストクラスとして定義するだけでは、どちらのルールか先に処理されるかを制御できません。<br>
 * ※Junitの実行時にどのルールが先に認識されるかは、JavaのバージョンやJVMの実装に依存するため。<br>
 * ルールの処理順序を制御する必要がある場合、RuleChainクラスを利用します。<br>
 * RuleChainクラスは、複数のルールを連鎖させることのできるルールです。いわゆるChain Of Repositoryパターンです。<br>
 * リスト9.20では、RuleChainクラスを利用してアプリケーションサーバ（AppServerルール）とデータベースサーバ（DBServerルール）の実行順序を制御しています。<br>
 * outerRuleメソッドで先に初期化し、後から終了処理を行う外側のルールを指定し、aroundメソッドで後に初期化して先に終了処理を行う内側のルールを指定します。<br>
 * JUnit4のRuleChainは、JUnit5（Jupiter）では直接的な1対1の代替機能が存在しませんが、Extensionモデル（@RegisterExtension）と順序付け（@Order）を組み合わせることで同等の機能を実現できます。<br>
 * RuleChainは「外から内へ」の順序を定義していましたが、JUnit5では@Orderを使って「1,2,3...」と順序を定義するスタイルへ移行します。<br>
 * 以下に、RuleChainの移行に関する具体的なステップと方法をまとめます。<br>
 * 1. 基本的な移行方針<br>
 * ・@Rule/@ClassRule/RuleChain→@RegisterExtension<br>
 * 順序の制御→@Order アノテーション<br>
 * 2. RuleChainからExtensionへの移行例<br>
 * 注：Extensionがフィールド（static）として登録される場合、順序は@Orderで指定します。<br>
 * 3. 具体的な移行ステップ<br>
 * A. 個別のRuleをExtensionに変換<br>
 * 既存のTestRuleを実装したクラスを、JUnit5のExtensionインターフェース（BeforeEachCallbackとAfterEachCallbackとBeforeAllCallback``AfterAllCallbackなど）に書き換えます。<br>
 * B.@RegisterExtensionで登録@Ruleの代わりにフィールドへ@RegisterExtensionを付与します。<br>
 * RuleChainでouterRuleが先、aroundが後に実行されていた順序を意識します。<br>
 * C.@Orderアノテーションで順序を保証<br>
 * JUnit5のExtensionはアルファベット順に実行されるため、明示的に順序を定義したい場合は、Extensionのクラスまたはフィールドに@Orderアノテーションを付与します。<br>
 * 
 * @author shuji.w6e
 */
class RuleChainExampleTest {

	// 順番を制御するために @Order を使用する
	@Order(1)
	@RegisterExtension
	static DBServer dbServer = new DBServer();

	@Order(2)
	@RegisterExtension
	static AppServer appServer = new AppServer();

	@Test
	void テスト() throws Exception {
		System.out.println("RuleChainによるルールの連鎖");
	}
}

class AppServer implements BeforeEachCallback, AfterEachCallback {
	@Override
	public void beforeEach(ExtensionContext context) throws Exception {
		System.out.println("start AP");
	}

	@Override
	public void afterEach(ExtensionContext context) throws Exception {
		System.out.println("shutdown AP.");
	}
}

class DBServer implements BeforeEachCallback, AfterEachCallback {
	@Override
	public void beforeEach(ExtensionContext context) throws Exception {
		System.out.println("start DB");
	}

	@Override
	public void afterEach(ExtensionContext context) throws Exception {
		System.out.println("shutdown DB.");
	}
}
