package com.example.demo.sample;

import lombok.AllArgsConstructor;

/**
 * 商品情報のクラス
 */
@AllArgsConstructor
public class Item {

	/** 商品名 */
	public final String name;
	
	/** 価格 */
	public final int price;
}
