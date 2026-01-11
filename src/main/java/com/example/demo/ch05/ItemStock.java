package com.example.demo.ch05;

import java.util.HashMap;
import java.util.Map;

/**
 * 商品を表すItemクラスが定義されている時、商品を追加/削除/数量の変更などができるItemStockクラスを作成する。</br>
 * 商品は商品名（name）で一意に識別できるものとする。 </br>
 * ・Itemクラスにaddメソッドを定義する。</br>
 * ・addメソッドはItem型の引数を1つ持ち、戻り値はvoidとする。</br>
 * ・ItemStockクラスは、指定したItemオブジェクトの数量を返すgetNumメソッドを持つ。</br>
 * ・getNumメソッドはItem型の引数を1つ持ち、戻り値はintとする。</br>
 * ・getNumメソッドは、指定したItemオブジェクトが登録されていない場合、0を返す。</br>
 */
public class ItemStock {

	private final Map<String, Integer> list = new HashMap<>();

	public void add(String item, int num) {
		int oldSize = size(item);
		int newSize = oldSize + num;
		list.put(item, newSize);
	}

	public int size(String item) {
		Integer size = list.get(item);
		return (size != null) ? size : 0;
	}

	public boolean contains(String item) {
		return 0 < size(item);
	}
}
