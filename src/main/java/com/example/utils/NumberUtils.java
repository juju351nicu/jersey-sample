package com.example.utils;

/**
 * 整数値を引数として持ち、偶数である場合にtrueを返すisEvenメソッドのテストを作成する。</br>
 * isEvenメソッドはstaticクラスとして定義する。</br>
 * ・NumberUtilsクラスにisEvenメソッドを定義する。</br>
 * ・isEvenメソッドはint型の引数をxを持ち、戻り値をboolean型とする。</br>
 * ・xが2で割り切れる場合にtrueを返す。</br>
 */
public class NumberUtils {

	/**
	 * ２で割れるかどうかを判定する。
	 * 
	 * @param x 入力値（整数）
	 * @return 判定結果
	 */
	public static boolean isEven(int x) {
		return x % 2 == 0;
	}
}
