package com.example.demo.ch07;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * リスト7.5 生成メソッドによるフィクスチャのセットアップ ・生成メソッドでのセットアップ</br>
 * 暗黙的セットアップは、セットアップメソッドに共通した初期化処理を抽出するパターンですが、テストクラスに共通した処理にしか適用できないという制約があります。</br>
 * つまり、複数のテストクラスでセットアップメソッドでん共通の初期化処理を行いたい場合に適用できません。</br>
 * このような場合、共通化した初期化処理を独立したクラスのメソッドに抽出する手法が有効です。</br>
 * このように、フィクスチャの生成メソッドを抽出し、フィクスチャのセットアップを行うパターンを、生成メソッド（creation
 * method）という。</br>
 * 生成メソッドでのセットアップでは、共通したコードをシンプルにメソッドとして抽出します。（BookStoreTestクラス・BookStoreTestHelperクラス）</br>
 * この時、テストクラスのprivateメソッドとして抽出することもできますが、独立したクラスにstaticメソッドとして定義するとよいでしょう。</br>
 * なぜならば、staticインポートを使うことができるようになるため、テストコードの可読性を高める事ができるからです。</br>
 * また、メソッド名を日本語で定義し、どんなフィクスチャをセットアップしているのかが一目で理解できるようにすると良い。</br>
 * 一般的に、長いメソッドは可読性が悪いため、いくつかのメソッドに分割することで整理します。</br>
 * プロダクションコードでは、さらに適切なクラスに分割したり、デザインパターンを適用したりする事で、コードを整理します。</br>
 * しかし、テストコードでは拡張性はあまり考慮せず、テストコードとして読みやすいかどうかを重視します。</br>
 * 
 * @author shuji.w6e
 */
class BookStoreTest {

	@Test
	void getTotalPrice() throws Exception {
		// SetUp
		BookStore sut = new BookStore();
		Book book = BookStoreDeclarativeTestHelper.Bookオブジェクトの作成_MartinFowlerのRefactoring();
		sut.addToCart(book, 1);
		// Exercise & Verify
		assertEquals(sut.getTotalPrice(), 4500);
	}

	@Test
	void get_0() throws Exception {
		// SetUp
		BookStore sut = new BookStore();
		Book book = BookStoreDeclarativeTestHelper.Bookオブジェクトの作成_MartinFowlerのRefactoring();
		sut.addToCart(book, 1);
		// Exercise & Verify
		assertEquals(sut.get(0), book);
	}
}
