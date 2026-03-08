package com.example.demo.ch11;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.Test;

/**
 * リスト11.19 RandomNumberGeneratorクラスのnextIntが呼び出されたかを検証するテスト
 * 
 * @author shuji.w6e
 */
class RandomsMockTest {

	@Test
	void choiceでAを返す() throws Exception {
		List<String> options = new ArrayList<>();
		options.add("A");
		options.add("B");
		Randoms sut = new Randoms();
		// モックの設定
		final AtomicBoolean isCallNextIntMethod = new AtomicBoolean(false);
		sut.generator = new RandomNumberGenerator() {
			@Override
			public int nextInt() {
				isCallNextIntMethod.set(true);
				return 0;
			}
		};
		assertEquals(sut.choice(options), "A");
		assertTrue(isCallNextIntMethod.get());
	}

}