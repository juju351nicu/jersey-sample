package com.example.demo.ch06;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * リスト6.5 構造化されていないArrayListのテストクラス
 * 
 * @author shuji.w6e
 */
class ArrayListFlatTest {

	private List<String> sut;

	@BeforeEach
	void setUp() throws Exception {
		sut = new ArrayList<>();
	}

	@Test
	void listに1件追加してある場合_sizeは1を返す() throws Exception {
		sut.add("A");
		int actual = sut.size();
		assertEquals(actual, 1);
	}

	@Test
	void listに2件追加してある場合_sizeは2を返す() throws Exception {
		sut.add("A");
		sut.add("B");
		int actual = sut.size();
		assertEquals(actual, 2);
	}
}
