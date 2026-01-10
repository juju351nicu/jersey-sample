package com.example.demo.sample;

/**
 * Counterクラのincrementメソッドは呼び出す毎に1ずつ加算する。</br>
 * 初回のincrementメソッド呼び出し時には1を返す。</br>
 * ・Counterクラスにincrementメソッドを定義する。</br>
 * ・incrementメソッドには現在値を保持する初期値0のint型フィールドを定義する。</br>
 * ・incrementメソッドは引数を持たず、戻り値をint型とする。</br>
 */
public class Counter {

	/** 初期値0 */
	int count = 0;

	/**
	 * 1ずつ加算する。
	 * 
	 * @return 加算された値
	 */
	public int increment() {
		return ++count;
	}
}
