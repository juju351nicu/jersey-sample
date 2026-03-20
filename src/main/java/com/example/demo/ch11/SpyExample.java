package com.example.demo.ch11;

import java.util.logging.Logger;

/**
 * ・DIユニットテスト<br>
 * ユニットテストで依存するオブジェクトをインタフェースなどを利用して差し替える手法は、DI（Dipendency
 * Injection依存性の注入）と相性の良い手法<br>
 * DIは「ソフトウェアはコンポーネントを組み合わせで構成される」という考え方に基づいています。<br>
 * 各コンポーネント間の依存関係はインタフェースを介して最小限とし、コンポーネント間の依存関係はDIコンテナと呼ばれるオブジェクトが解決するというのがDIの基本です。<br>
 * F コンポーネント間の依存関係を小さくし、分離することで、各コンポーネントが再利用しやすく独立性を高くできます。<br>
 * DIはSpringフレームワークやSeaser２などのフレームワークでよく使われています。<br>
 * 主な目的は、コンポーネントの依存関係を最小化することです。<br>
 * その結果、ソフトウェアの瀬ぅtけいが適切にレイヤ分割されます。<br>
 * そして、依存オブジェクトを仮実装したテストダブルで置き換えることが簡単になるため、ユニットテストも行いやすくなります。<br>
 * リスト11.20 リスト11.31 ロガーを扱うクラス
 * 
 * @author shuji.w6e
 */
public class SpyExample {

	Logger logger = Logger.getLogger(SpyExample.class.getName());

	public void doSomething() {
		logger.info("doSomething");
	}
}
