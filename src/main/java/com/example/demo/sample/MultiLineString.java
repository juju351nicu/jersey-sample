package com.example.demo.sample;

/**
 * 引数で指定した複数の文字列を改行で連結して返すメソッドのテストを作成する。</br>
 * メソッドの引数は可変長とする。</br>
 * 改行コードはシステムの標準の改行コードとする。</br>
 * また、テストが失敗した時にアサーションに失敗した箇所を識別しやすいようにカスタムMatcherクラスを作成する。</br>
 * ・MultiLineStringクラスにjoinメソッドを定義する。</br>
 * ・joinメソッドはstaticメソッドとする。</br>
 * ・joinメソッドはString型の引数を可変超引数として持ち、戻り値をString型とする。</br>
 * ・連結するときの改行コードはシステム環境変数から取得する。</br>
 */
public class MultiLineString {
	public static String join(String... lines) {
		if (lines == null)
			return null;
		String lineSeparator = System.getProperty("line.separator");
		StringBuilder text = new StringBuilder();
		for (String line : lines) {
			text.append(line != null ? line : "").append(lineSeparator);
		}
		return text.toString();
	}

}
