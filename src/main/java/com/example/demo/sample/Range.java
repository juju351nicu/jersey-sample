package com.example.demo.sample;

import lombok.AllArgsConstructor;

/**
 * 浮動小数点の範囲を表すRangeクラスがある。</br>
 * このRangeクラスに、浮動小数点を指定して範囲内であるかどうかを判定するcontainsメソッドを定義し、そのテストを作成する。</br>
 * なお、Rangeクラスのcontainsメソッドの判定では、範囲の両端は範囲内であるとする。</br>
 * ・Rangeクラスにcontainsメソッドを定義する。</br>
 * ・containsメソッドは引数にdouble型の引数を1つ持ち、戻り値をbooleanにする。</br>
 */
@AllArgsConstructor
public class Range {

	/** 最小値 */
	public final double min;

	/** 最大値 */
	public final double max;

	/**
	 * 該当する浮動小数点の範囲内であるかどうかを判定する。
	 * 
	 * @param value 浮動小数点の数値
	 * @return 判定結果
	 */
	public boolean contains(double value) {
		return min <= value && value <= max;
	}

}
