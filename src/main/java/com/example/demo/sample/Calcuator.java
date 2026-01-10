package com.example.demo.sample;

/**
 * Calcuatorクラスに整数の割り算を行うdivideメソッドを作成し、0で割る場合に例外が送出されることを検証するテストを作成する。</br>
 * 整数の割り算は小数点以下を切り捨てる。</br>
 * ・Calcuatorクラスにdivideメソッドを定義する。</br>
 * ・divideメソッドは、int型の引数を2つ持ち、戻り値をint型とする。</br>
 * ・戻り値は第1引数を第２引数で割った値とする。</br>
 * ・第２引数が0である場合、IllegalArgumentExceptionを送出する。</br>
 */
public class Calcuator {

	/** エラーメッセージ */
	private final static String errorMessage = "y is zero.";

	/**
	 * 整数の割り算を行う</br>
	 * 0で割る場合に例外が送出される
	 * 
	 * @param x 分子
	 * @param y 分母
	 * @return 算出結果
	 */
	public int divide(int x, int y) {
		if (y == 0) {
			throw new IllegalArgumentException(errorMessage);
		}
		return x / y;
	}
}
