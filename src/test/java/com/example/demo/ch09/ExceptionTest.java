package com.example.demo.ch09;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * リスト9.9 例外の発生を検証する標準的なテスト<br>
 * ExpectedExceptionクラスは、送出された例外を詳細に検証するためのルールです。<br>
 * Junitで例外の送出を検証する場合、リスト9.9のようにTestアノテーションのexpected属性に例外クラスを指定します。<br>
 * この記法はJunitの標準的な記法ですが、例外メッセージなど詳細な検証ができません。<br>
 * 例外メッセージなどを検証する場合、リスト9.10のように例外オブジェクトの参照を取得し、検証用のコードを書く必要があります。<br>
 * このテストコードは期待通りのテストを実施できますが、検証用のコードがcatch節にあったり、例外が送出されなかった場合のfailメソッドが必要であったりと、非常に読みにくいコードとなっています。<br>
 * 一方、ExpectedExceptionクラスを利用すれば、簡単に詳細な検証を行うことができます。(リスト9.11)<br>
 * 例外のメッセージまでを含めてユニットテストをする機会はありませんが、ExpectedExceptionクラスは例外オブジェクトに対して詳細な検証が必要な場合に有効なルールです。<br>
 * リスト9.10 例外の発生とメッセージを検証する標準的なテスト<br>
 * 
 * @author shuji.w6e
 */
class ExceptionTest {

	@Test
	void 例外の発生を検証する標準的なテスト() throws Exception {
		// 例外が発生する処理（例：負の引数を渡す）
		assertThrows(IllegalArgumentException.class, () -> {
			throw new IllegalArgumentException("無効な引数です");
		});
	}

	@Disabled("例外が発生しないため、テストは失敗する")
	@Test
	void 例外の発生とメッセージを検証する標準的なテスト() {
		try {
			throwNewIllegalArgumentException();
			fail("例外が発生しない");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "argument is null.");
		}
	}

	private void throwNewIllegalArgumentException() {
		throw new IllegalArgumentException();
	}

}
