package com.example.demo.sample;

import lombok.AllArgsConstructor;

/**
 * 消費税を表すConsumptionTaxクラスを作成し、金額を引数に与えると消費税込みの金額を返すメソッドのテストを作成せよ。</br>
 * なお、消費税はコンストラクタで指定し、税額は小数点以下を切り捨てとする。</br>
 * ・ConsumptionTaxクラスはコンストラクタにint型引数をもち、税率としてフィールドに保持する。</br>
 * ・ConsumptionTaxクラス、applyメソッドを定義する。</br>
 * ・applyメソッドは、int型引数を1つ持ち、戻り値をint型とする。</br>
 */
@AllArgsConstructor
public class ConsumptionTax {
	/** 税率 */
	private final int rate;

	public int apply(int price) {
		return price + (price * this.rate / 100);
	}
}
