package com.example.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文字列をスネークケース（全て小文字で単語区切りがアンダースコア）に変換するユーティリメソッドのテストを作成せよ。</br>
 * ・StringUtilsクラスを定義する。</br>
 * ・StringUtilsクラスにtoSnakeCaseメソッドを定義する。</br>
 * ・toSnakeCaseメソッドは、publicかつstaticメソッドとする。</br>
 * ・toSnakeCaseメソッドは、String型の引数を1つ持ち、戻り値をString型とする。</br>
 */
public class StringUtils {
	public static String toSnakeCase(String text) {
		if (text == null) {
			throw new NullPointerException("text is null");
		}
		String snake = text;
		Pattern p = Pattern.compile("([A-Z])");
//		Matcher m = p.matcher(snake);
//		if (m.find()) {
//			snake = m.replaceFirst("_" + m.group(1).toLowerCase());
//		}
		for (int i = 0; i < snake.length(); i++) {
			Matcher m = p.matcher(snake);
			if (!m.find()) {
				break;
			}
			snake = m.replaceFirst("_" + m.group(1).toLowerCase());
		}
		return snake.replaceFirst("^_", "");
	}
}
