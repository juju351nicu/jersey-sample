package com.example.demo.sample;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

/**
 * 戻り値がコレクションで、順序も含め全ての要素を検証する場合、「test_nomarl」のようにサイズと各要素のアサーションを行うのがシンプルな方法です。</br>
 * それぞれの要素を個別に検証すれば、テストが失敗した時に、何番目の要素で検証が失敗したかを簡単に知る事ができる。</br>
 * しかしこの場合、検証用のコードが長く、冗長になる。</br>
 * 一方、「test_nomarl2」のように期待値となるリストを初期化処理で作成しアサーションを行う方法もあります。</br>
 * 検証用のコードは短くなりましたが、テストが失敗した場合に何番目の要素で検証が失敗したのか分かりにくくなる。</br>
 * この場合、両方のメリットを持つカスタムMatcherを作成するとよい。</br>
 */
class FizzBuzzTest {

	/**
	 * 16を指定してcreateFizzBuzzListメソッドを実行すると「1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz,
	 * Buzz, 11, Fizz, 13, 14, FizzBuzz, 16」が取得できる。
	 */
	@Test
	@DisplayName("createFizzBuzzListで16まで取得できる")
	void test_nomarl1() {
		List<String> actual = FizzBuzz.createFizzBuzzList(16);
		// Verify
		assertNotNull(actual);
		assertEquals(actual.size(), 16);
		assertEquals(actual.get(0), "1");
		assertEquals(actual.get(1), "2");
		assertEquals(actual.get(2), "Fizz");
		assertEquals(actual.get(3), "4");
		assertEquals(actual.get(4), "Buzz");
		assertEquals(actual.get(5), "Fizz");
		assertEquals(actual.get(6), "7");
		assertEquals(actual.get(7), "8");
		assertEquals(actual.get(8), "Fizz");
		assertEquals(actual.get(9), "Buzz");
		assertEquals(actual.get(10), "11");
		assertEquals(actual.get(11), "Fizz");
		assertEquals(actual.get(12), "13");
		assertEquals(actual.get(13), "14");
		assertEquals(actual.get(14), "FizzBuzz");
		assertEquals(actual.get(15), "16");
	}

	@Test
	void test_nomarl2() {
		List<String> expected = new ArrayList<String>(16);
		expected.add("1");
		expected.add("2");
		expected.add("Fizz");
		expected.add("4");
		expected.add("Buzz");
		expected.add("Fizz");
		expected.add("7");
		expected.add("8");
		expected.add("Fizz");
		expected.add("Buzz");
		expected.add("11");
		expected.add("Fizz");
		expected.add("13");
		expected.add("14");
		expected.add("FizzBuzz");
		expected.add("16");
		List<String> actual = FizzBuzz.createFizzBuzzList(16);
		assertEquals(actual, expected);
	}

	@Test
	void createFizzBuzzListで6まで取得できる() {
		List<String> actual = FizzBuzz.createFizzBuzzList(6);
		assertEquals(actual, List.of("1", "2", "Fizz", "4", "Buzz", "Fizz"));
	}
}
