package com.example.demo.ch09;

import org.junit.jupiter.api.extension.Extension;

/**
 * 9.3カスタムルールの作成<br>
 * 前節では、Junitが提供するルールを解説しましたが、ルールの最大の特徴は簡単にカスタムルールを作成できることです。<br>
 * つまり、カスタムルールを作成できることです。<br>
 * つまりカスタムルールを作成すれば簡単にユニットテストの実行を拡張することができます。<br>
 * カスタムルールを作るには、TestRuleインターフェースを実装したクラスを作成します。<br>
 * あるいは、ExternalResourceクラスなどの既存のルールのサブクラスを作成します。<br>
 * TestRuleインターフェースには次のメソッドが定義されています。<br>
 * Statement apply(Statement base,Description description);<br>
 * Statementオブジェクトは、テストの実行を制御するオブジェクトです。<br>
 * Statementクラスに定義されたevaluteメソッドが呼び出されるとテストが実行されます。<br>
 * applyメソッドに引数として渡されるStatementオブジェクトはJunitの通常テスト実行を行うStatementオブジェクトです。<br>
 * evaluteメソッドを呼び出すと次のようにテストが実行されます。<br>
 * ❶テストクラスのインスタンスを生成する<br>
 * ❷Beforeアノテーションの付与されたメソッドを実行する（事前処理）<br>
 * ❸テストメソッドを実行する<br>
 * ❹Afterアノテーションの付与されたメソッドを実行する（事後処理）<br>
 * Descriptionオブジェクトは、テストケースのメタ情報を保持するオブジェクトであり、そこからテストクラスの名前やテストメソッドの名前、付与されたアノテーションなどを取得できます。<br>
 * applyメソッドの戻り値はStatemtent型です。<br>
 * ルールの一般的な実装では、引数として渡されたStatementオブジェクトのプロキシオブジェクトを作成して返します。<br>
 * つまり、オリジナルのevaluteメソッドを実行する前後に独自の処理を行うStatementオブジェクトを生成します。<br>
 * リスト9.16 事前条件をチェックするカスタムルール<br>
 * リスト9.16のPreConditonクラスは、テストの事前条件をチェックするカスタムルールです。<br>
 * PreConditionクラスの実装は、Verfierクラスの実装を参考にしました。<br>
 * applyメソッドでは引数として渡されるStatementオブジェクトのプロキシオブジェクトを返します。<br>
 * プロキシオブジェクトのevaluteメソッドでは、オリジナルのStatementオブジェクトに定義されたevaluteメソッドを呼び出す前に、事前チェックを行うverfyメソッドを呼び出します。<br>
 * verifyメソッドはPreConditionクラスのサブクラスでオーバライドします。<br>
 * verifyメソッドで例外が送出された場合、テストは実行されず、テストは失敗となります。<br>
 * このように、カスタムルールを作成すれば、テストの実行前後に独自の処理を行うことができます。<br>
 * 初期化処理メソッドや後処理メソッドで処理を行う場合と比較し、よりテストクラスから独立した形で拡張機能が提供できます。<br>
 * また、テストに関する多くのメタ情報を使うこともできます。<br>
 * 
 * @author shuji.w6e
 */
public abstract class PreCondition implements Extension {

}