package com.example.demo.ch11;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * リスト11.15 乱数生成オブジェクトのスタブを利用したテスト
 * 
 * @author shuji.w6e
 */
class RandomsTest {

	@Test
	void choiceでAを返す() throws Exception {
		List<String> options = new ArrayList<>();
		options.add("A");
		options.add("B");
		Randoms sut = new Randoms();
		// スタブの設定
		sut.generator = new RandomNumberGenerator() {
			@Override
			public int nextInt() {
				return 0;
			}
		};
		assertEquals(sut.choice(options), "A");
	}

	@Test
	void choiceでBを返す() throws Exception {
		List<String> options = new ArrayList<>();
		options.add("A");
		options.add("B");
		Randoms sut = new Randoms();
		// スタブの設定
		sut.generator = new RandomNumberGenerator() {
			@Override
			public int nextInt() {
				return 1;
			}
		};
		assertEquals(sut.choice(options), "B");
	}

}
