package com.example.demo.sample;

import java.util.ArrayList;
import java.util.List;

/**
 * 1から指定された値までの値を文字列に変換し、リストとして返すクラスのテストを作成する。</br>
 * 値を3の倍数の場合は「Fizz」を、値が5の倍数の場合は「Buzz」を値が3の倍数かつ5の倍数の場合は「FizzBuzz」を値の代わりにリストに格納すること。</br>
 * 例えば、4を指定した場合は「1,2,Fizz,4」を返す。</br>
 * ・FizzBuzzクラスにcreateFizzBuzzListメソッドを定義する。</br>
 * ・createFizzBuzzListメソッドはstaticメソッドとする。</br>
 * ・createFizzBuzzListメソッドは、int型の引数を1つ持ち、戻り値をList<String>型とする。</br>
 * ・引数がマイナスの場合などの例外処理は省略する。</br>
 */
public class FizzBuzz {

	/**
	 * 1から指定された値までの値を文字列に変換し、リストとして返すクラス
	 * 
	 * @param size 1から指定されたまでの値
	 * @return 変換された文字列リスト
	 */
	public static List<String> createFizzBuzzList(int size) {
		List<String> list = new ArrayList<>();
		for (int i = 1; i <= size; i++) {
			if (i % 15 == 0) {
				list.add("FizzBuzz");
			} else if (i % 3 == 0) {
				list.add("Fizz");
			} else if (i % 5 == 0) {
				list.add("Buzz");
			} else {
				list.add(Integer.toString(i));
			}
		}
		return list;
	}
}
