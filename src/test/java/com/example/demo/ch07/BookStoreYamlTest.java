package com.example.demo.ch07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

/**
 * リスト7.8 YnakeYamlによるYAMLファイルの読み込み ・外部リソースからのセットアップ</br>
 * フィクスチャのセットアップを生成メソッドに抽出すればテストコードの可読性は高くなります。</br>
 * しかしながら、複雑なオブジェクトの生成を行うコードが消えるわけではありません。</br>
 * Javaでは、フィクスチャのセットアップのようなデータを生成するコードは読みやすくありません。</br>
 * これは、Javaの言語使用では宣言的なコードを記述しにくいためです。</br>
 * setterメソッドやputメソッドなどの手続き的なメソッド呼び出しではなく、そのデータがどんな値を持っているのかを宣言的に記述できればコードは読みやすくなります。</br>
 * このため、外部に定義したリソースファイルにテストデータを記述し、生成メソッドなどで読み込む手法が有効です。</br>
 * リソースファイルには、YAML、XML,JSON,CSV,Excelなどのフォーマットが利用できます。</br>
 * 絶対的に使いやすいフォーマットはない為、フィクスチャの特徴によって使い分けるとよいでしょう。</br>
 * なお、XMLは複雑なテストデータも表現することができますが、記述量が多く専用のエディタを使わなければメンテナンスしにくいためあまりお勧めできません。</br>
 * 利用する場合は、外部ツールなどを使いフィクスチャを管理し、リソースファイルは自動生成すると良いでしょう。</br>
 * JSONは複数行テキストの扱いに難がありますが、Webサービスなどでは出力形式としてよく使われます。</br>
 * フィクスチャがリスト構造で大量に必要ならばCSVやExcelが便利です。</br>
 * 筆者のお勧めはシンプルでXMLと同程度の表現力があるYAMLです。</br>
 * BookStoreYamlTestクラスはYAMLを使っている。</br>
 * YAMLファイルは外部ライブラリを利用して読み込めば簡単にオブジェクトに変換できる。</br>
 * BookStoreYamlTestクラスはYAMLを読み書きするライブラリSnakeYamlを使ってテストデータを読み込んでいます。</br>
 * なお、外部リソースからのセットアップでは、テストケースとテストデータがそれぞれ独立したファイルに定義されるため、相互参照を行いにくい事が難点です。</br>
 * 
 * @author shuji.w6e
 */
class BookStoreYamlTest {

	@Test
	void getTotalPrice() throws Exception {
		// SetUp
		BookStore sut = new BookStore();
		Book book = (Book) new Yaml().load(getClass().getResourceAsStream("book_fixtures.yaml"));
		sut.addToCart(book, 1);
		// Exercise & Verify
		assertEquals(sut.getTotalPrice(), 4500);
	}

	@Test
	void get_0() throws Exception {
		// SetUp
		BookStore sut = new BookStore();
		Book book = (Book) new Yaml().load(getClass().getResourceAsStream("book_fixtures.yaml"));
		sut.addToCart(book, 1);
		// Exercise & Verify
		assertEquals(sut.get(0), book);
	}
}
