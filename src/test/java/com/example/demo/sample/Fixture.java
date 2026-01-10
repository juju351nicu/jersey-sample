package com.example.demo.sample;

import lombok.AllArgsConstructor;

/**
 * 下記では、税率、入力値（金額）、期待値をフィールドとして持つFixtureクラスを定義してる。</br>
 * Fixtureクラスでは、パラメータ化テストのパラメータとしてテストクラスに定義されています。</br>
 */
@AllArgsConstructor
public class Fixture {
	/** 税率 */
	final int taxRate;
	/** 入力値（金額） */
	final int price;
	/** 期待値 */
	final int expected;
}
