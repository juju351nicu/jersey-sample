package com.example.demo.sample;

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
	private final Map<String, Integer> values = new HashMap<>();

	/**
	 * 商品を追加する
	 * 
	 * @param item 商品情報
	 */
	public void add(Item item) {
		Integer num = values.get(item.name);
		if (num == null) {
			num = 0;
		}
		num++;
		values.put(item.name, num);
	}

	/**
	 * 商品を取得する
	 * 
	 * @param item 商品情報
	 * @return 商品が登録されていれば商品を返す。登録されていなければ、0を返す。
	 */
    public int getNum(Item item) {
        Integer num = values.get(item.name);
        return num != null ? num : 0;
    }
}
